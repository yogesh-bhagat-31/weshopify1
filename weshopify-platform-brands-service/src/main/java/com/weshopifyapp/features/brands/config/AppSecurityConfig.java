package com.weshopifyapp.features.brands.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.context.SecurityContextPersistenceFilter;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.web.filter.DelegatingFilterProxy;

import lombok.extern.slf4j.Slf4j;

@Configuration // Required for creating the bean for security filter chain its equilvalent to				// xml file.
@Slf4j
public class AppSecurityConfig{
    
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	BCryptPasswordEncoder encoder;
	
	@Autowired
	private UserAuthzService userAuthzService;
	
	@Bean
	public InMemoryUserDetailsManager userDetails() {
			
		UserDetails user = User.withUsername("user")
							   .password(encoder.encode("user"))
			                   .roles("user")
			                   .build();		
		return new InMemoryUserDetailsManager(user);
				
	}
	
	 	
	//In below method we are writing access decisions.
	@Bean // SecurityFilterChain will be created as spring bean by IOC container.
	// SecurityFilterChain will give us list of filters
	public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception { 		
		httpSecurity.authorizeHttpRequests(request-> {
			
			try {
				request
					 .anyRequest()
					 .authenticated()
					 .and()
					 .cors()
					 .disable()
					 .csrf()
					 .disable()
					 .anonymous()
					 .disable()
					 .addFilterBefore(new BrandsAuthzFilter(userAuthzService), BasicAuthenticationFilter.class);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});		
		
		return httpSecurity.build();
	}
	
	
	@Bean
	public WebSecurityCustomizer webSecurityCustomizer() {
		log.info("Ignoring the security...");
		return (web) -> web.ignoring()
				.requestMatchers("/swagger-ui.html","/swagger-ui/**","/v3/api-docs/**");
		
	}
	


}


