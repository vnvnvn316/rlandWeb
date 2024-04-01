package kr.co.rland.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class WebSecurityConfig {

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
				.permitAll()
			)
      .logout((logout) -> logout
				.logoutUrl("/user/logout")
        .logoutSuccessUrl("/index")
				.permitAll()
			);

		return http.build();
	}
  
  @Bean
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
}
