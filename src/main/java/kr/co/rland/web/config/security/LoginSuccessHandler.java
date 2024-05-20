package kr.co.rland.web.config.security;

import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.nimbusds.jose.proc.SecurityContext;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Component
public class LoginSuccessHandler implements AuthenticationSuccessHandler {

    //url 인코딩에 도움을 주는 애
    private RedirectStrategy redirectStrategy= new DefaultRedirectStrategy();

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {
        
            System.out.println("===========succesHandler============");

            String url = "/";
            
            WebUserDetails userDetails = (WebUserDetails) authentication.getPrincipal();
            
            //만약에 로컬에 있는 이메일과 구글로그인의 이메일이 다른경우 권한이 부여되지않음
            //권한이 없는 경우 로컬 회원가입을 하라고 리다이렉트해 줌
            if(userDetails.getAuthorities()== null) {
                
                //스프링시큐리티의 남아있는 사용자정보 없애줘야 함 (구글 로그인 정보)
                request.logout(); //request에서 지워 봄 -> 효과없음
                SecurityContextHolder.clearContext(); // 얘는 읽기전용이라서 clear해봤자 소용없음
                
                //세션에서 정보를 지워주는게 깔끔하다
                HttpSession session = request.getSession(false); // session()안에 꼭 false를 사용해야함. false 안적으면 세션이 없으면  새로 세션을 만들기 때문
                
                if(session != null)
                    session.invalidate(); //세션에 있는 인증정보 사라짐
                
                url="/user/signup";
            }
            
            // else
            //     url="/admin/index";
            
            redirectStrategy.sendRedirect(request, response, url);

    }


    
}
