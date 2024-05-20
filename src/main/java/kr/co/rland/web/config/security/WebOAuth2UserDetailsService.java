package kr.co.rland.web.config.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.DelegatingOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import kr.co.rland.web.entity.Member;
import kr.co.rland.web.entity.MemberRole;
import kr.co.rland.web.repository.MemberRepository;
import kr.co.rland.web.repository.MemberRoleRepository;

@Service
public class WebOAuth2UserDetailsService implements OAuth2UserService<OAuth2UserRequest,OAuth2User>{

    @Autowired
    private MemberRepository repository;

     @Autowired
    private MemberRoleRepository memberRoleRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        
        OAuth2UserService<OAuth2UserRequest,OAuth2User> service = new DefaultOAuth2UserService(); //OAuth로 온 데이터를 담아주는 역할
        OAuth2User oAuth2User = service.loadUser(userRequest);

        String username = oAuth2User.getAttribute("name"); //구글의 로그인 아이디
        String email = oAuth2User.getAttribute("email");

        Member member = repository.findByEmail(email);

        WebUserDetails userDetails = new WebUserDetails();
        userDetails.setAttributes(oAuth2User.getAttributes());
        userDetails.setName(oAuth2User.getName()); 
        userDetails.setUsername(username); // 이메일이 로컬에 있는 이메일과 다른 경우 대비
    
        //회원 존재안하면 기본정보만 담아서 반환
        if(member==null) // local 회원으로 가입한 적이 없다.
            return userDetails; //그럼 담을 것이 없으니 그냥 리턴, websecurityconfig에서 실패할 경우 처리! 여기서 redirection하거나 다른 업무로직을 사용하면 안됌
        
        // 회원 정보가 있을 경우 spring security가 사용할 정보 담아 줌
        // ----------security info -----------------
        List<MemberRole> roles = memberRoleRepository.findAllByMemberId(member.getId());

        List<GrantedAuthority> authorities = new ArrayList<>();
        
        for(MemberRole role : roles)
            authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
        
        userDetails.setId(member.getId());
        userDetails.setUsername(member.getUsername());
        userDetails.setEmail(member.getEmail());
        userDetails.setPassword(member.getPwd());
        userDetails.setAuthorities(authorities);

        return userDetails;
        

        // System.out.println(oAuth2User);

        // System.out.println("------------authorities----------------");
        // System.out.println(oAuth2User.getAuthorities());
        // System.out.println("------------name----------------");
        // System.out.println(oAuth2User.getName());

        // System.out.println("------------token----------------");
        // System.out.println(userRequest.getAccessToken());
        // System.out.println("------------client registration ----------------");
        // System.out.println(userRequest.getClientRegistration().getRegistrationId()); //클라이언트가 구글
    
    }
    
}
