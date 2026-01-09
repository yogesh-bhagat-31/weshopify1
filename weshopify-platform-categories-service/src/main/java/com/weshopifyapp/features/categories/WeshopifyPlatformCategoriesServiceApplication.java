package com.weshopifyapp.features.categories;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;



@SpringBootApplication
@EnableDiscoveryClient
public class WeshopifyPlatformCategoriesServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(WeshopifyPlatformCategoriesServiceApplication.class, args);
	}

	@Bean
	ModelMapper modelMapper() {
		return new ModelMapper();

	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();

	}

}
