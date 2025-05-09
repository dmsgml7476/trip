package com.trip.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity

public class SecurityConfig {
	
	 @Bean
	    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
	        http
	            .authorizeHttpRequests(auth -> auth
	            	.requestMatchers("/mypage/**").authenticated()
	                .anyRequest().permitAll()  
	            )
	            .formLogin (
		        		form->form
		        			.loginPage("/login")
		        			.defaultSuccessUrl("/")
		        			.usernameParameter("loginId")
		        			.failureUrl("/login/error")
		        			.permitAll()
		        			)
	            .logout(logout->logout
	            		.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
	            		.logoutSuccessUrl("/")
	            		.invalidateHttpSession(true)
	            		)
	            .csrf(csrf -> csrf.disable())
	            .headers(headers -> headers.disable()); 
	        	
	        return http.build();
	    }
	 @Bean
	 public PasswordEncoder passwordEncoder()	{
		 return new BCryptPasswordEncoder();
	 }

}
