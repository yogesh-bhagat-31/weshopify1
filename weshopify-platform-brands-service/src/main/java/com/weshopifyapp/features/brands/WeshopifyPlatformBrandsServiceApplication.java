package com.weshopifyapp.features.brands;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
@EnableMongoRepositories
@EnableFeignClients
@EnableDiscoveryClient
public class WeshopifyPlatformBrandsServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(WeshopifyPlatformBrandsServiceApplication.class, args);

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
