package com.weshopifyapp.features.users;

import org.modelmapper.ModelMapper;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class WeshopifyUsermanagementServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(WeshopifyUsermanagementServiceApplication.class, args);
	}

	@Bean
	ModelMapper modelMapper() {
		return new ModelMapper();

	}

	@Bean
	RestTemplate restTemplate() {
		return new RestTemplate();
	}

}
