package kr.co.rland.web.config.security;

import java.io.IOException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import kr.co.rland.web.entity.Member;

@Configuration
public class WebSecurityConfig {

  @Autowired
  private DataSource dataSource;

  @Autowired
  private WebOAuth2UserDetailsService oauth2UserDetailsService;

  @Autowired
  private LoginSuccessHandler loginSuccessHandler;

  @Bean
  public PasswordEncoder passwordEncoder (){
    PasswordEncoder encoder = new BCryptPasswordEncoder();
    return encoder;
  }


  @Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
      .csrf((csrf) -> csrf.disable())
			.authorizeHttpRequests((requests) -> requests
        .requestMatchers("/member/**").hasAnyRole("MEBER","ADMIN")
        .requestMatchers("/admin/**").hasRole("ADMIN")
       // .requestMatchers("/member/**").authenticated()
				.anyRequest().permitAll()
			)
			.formLogin((form) -> form
				.loginPage("/user/signin")
        //.successHandler(new AuthSuccessHandler())
        .successHandler(loginSuccessHandler)
				.permitAll()
        
			)
      .oauth2Login(config->config
        .userInfoEndpoint(userInf->userInf
          .userService(oauth2UserDetailsService)) //구글의 로그인정보를 token으로 받는 것
        .successHandler(loginSuccessHandler)
        )
      .logout((logout) -> logout
				.logoutUrl("/user/logout")
        .logoutSuccessUrl("/index")
				.permitAll()
			);

		return http.build();
	}
  
  //데이터베이스 쿼리를 해서 사용자 정보를 제공하는 제공자
  //@Bean
  public UserDetailsService jdbcUserDetailsService(){
    
        //      -> 결과 집합의 모양
    //         ┌────────────┬───────────┬─────────┐
    //         │  username  │  password │ enabled │
    //         ├────────────┼───────────┼─────────┤
    //         │   newlec   │    111    │    1    │   

    String userSql ="select username, pwd password, 1 enabled from member where username= ?";

    //""" text block (jdk 15이상만 가능) """
    String authorSql = """
            select
            m.username,
          mr.role_name authority
          from
            member m 
            right join member_role mr
              ON m.id = mr.member_id
          where m.username=?
    """;

    JdbcUserDetailsManager manager = new JdbcUserDetailsManager(dataSource);
    manager.setUsersByUsernameQuery(userSql);
    manager.setAuthoritiesByUsernameQuery(authorSql);

    return manager;

  }


  //메모리상의 사용자 정보 제공자
 //@Bean
   public UserDetailsService userDetailsService() {
      UserDetails user1 =
          User.builder()
            .username("newlec")
            .password("{noop}111")
            .roles("USER","ADMIN")
            .build();
        
        UserDetails user2 =
            User.builder()
              .username("dragon")
              .password("{noop}111")
              .roles("USER")
              .build();

      return new InMemoryUserDetailsManager(user1,user2);
   }

  //  class AuthSuccessHandler implements AuthenticationSuccessHandler{

  //   @Override
  //   public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
  //           Authentication authentication) throws IOException, ServletException {
  //       HttpSession session = request.getSession();
  //       String username = authentication.getName();
  //       Member member = memberRepository.findByUsername(username);
  //       session.setAttribute("email", member.getEmail());
  //   }
    
  // }

}
