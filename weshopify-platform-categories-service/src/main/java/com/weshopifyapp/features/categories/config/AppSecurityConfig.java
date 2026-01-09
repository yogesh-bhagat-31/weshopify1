package com.weshopifyapp.features.categories.config;


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

@Configuration // Required for creating the bean for security filter chain its equilvalent to
				// xml file.
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
	
	 
//	//In below method we are writing access decisions.
//	@Bean // SecurityFilterChain will be created as spring bean by IOC container.
//	// SecurityFilterChain will give us list of filters
//	public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception { 		
//		httpSecurity		
//		.authorizeHttpRequests(request ->request.anyRequest().authenticated())  // you are making an access decision in your code by specifying that any request must be authenticated to be allowed.
//		.csrf(csrf -> csrf.disable()) // Do not enable the CSRF
//		.cors(cors -> cors.disable()) //CORS stands for Cross-Origin Resource Sharing
//		.anonymous(anonymous -> anonymous.disable())
//		.addFilterBefore(new CategoriesAuthzFilter(userAuthzService),BasicAuthenticationFilter.class); // Here CategoriesAuthzFilter should not be spring managed.
//		//.httpBasic(Customizer.withDefaults());// we are using basic authintication.
//			 	    
//		return httpSecurity.build();
//	}
	
   
	
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
					 .addFilterBefore(new CategoriesAuthzFilter(userAuthzService), BasicAuthenticationFilter.class);
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
	
//	@Bean
//	public CategoriesAuthzFilter categoriesAuthzFilter() {
//		CategoriesAuthzFilter authz = new CategoriesAuthzFilter(userAuthzService);
//		System.out.println("CategoriesAuthzFilter instantiated");
//		return authz;
//	}

}

//CORS:-(Cross-Origin Resource Sharing)
//Imagine you are using a web application that has two parts:
//
//Frontend: This is the part of the application that runs in your web browser.
//It's typically built with HTML, CSS, and JavaScript and is responsible for displaying the user interface.

//Backend/API: This is the server-side part of the application that handles data processing and storage.
//It exposes a RESTful API that the frontend communicates with to fetch or submit data.

//Let's say:
//
//The frontend is hosted at https://www.example.com.
//The backend/API is hosted at https://api.example.com.

//Now, let's consider a scenario:
//
//You open your web browser and navigate to https://www.example.com.
//The frontend code is served to your browser, and it includes JavaScript 
//code that makes an AJAX request to fetch some data from the API hosted at https://api.example.com.
//When the browser sends this AJAX request, it includes an "Origin" header that indicates
//where the request originated from (https://www.example.com in this case).
//The backend/API receives the request and checks the "Origin" header to determine
//if the request is coming from an allowed domain.
//If the backend/API allows requests from https://www.example.com, 
//it responds to the request as expected.
//However, if the backend/API does not allow requests
//from https://www.example.com (for example, if CORS is not properly configured), 
//it will reject the request. The browser, in turn, will block the response from 
//being delivered to the frontend JavaScript code, thus preventing the frontend from accessing the data.
//In this scenario, CORS prevents the frontend code served from 
//https://www.example.com from accessing resources
//(in this case, the API hosted at https://api.example.com)
//on a different domain without proper authorization.
//It helps protect against unauthorized cross-origin requests and 
//helps ensure the security of web applications.
