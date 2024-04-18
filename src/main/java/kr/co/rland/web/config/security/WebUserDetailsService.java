package kr.co.rland.web.config.security;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import kr.co.rland.web.entity.Member;
import kr.co.rland.web.entity.MemberRole;
import kr.co.rland.web.repository.MemberRepository;
import kr.co.rland.web.repository.MemberRoleRepository;

@Service
public class WebUserDetailsService implements UserDetailsService{

    @Autowired
    private MemberRepository repository;

    @Autowired
    private MemberRoleRepository memberRoleRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        
        Member member = repository.findByUsername(username);
        List<MemberRole> roles = memberRoleRepository.findAllByMemberId(member.getId());

        WebUserDetails userDetails = new WebUserDetails();
        userDetails.setId(member.getId());
        userDetails.setUsername(member.getUsername());
        userDetails.setEmail(member.getEmail());
        userDetails.setPassword(member.getPwd());
       // userDetails.setAuthorities();

        return null;
    }
    
}
