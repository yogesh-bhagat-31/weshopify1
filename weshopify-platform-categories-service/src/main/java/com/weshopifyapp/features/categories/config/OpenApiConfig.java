package com.weshopifyapp.features.categories.config;

import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityScheme;

@Configuration

@OpenAPIDefinition(info = @Info(title = "Categories-Service",
description = "Categories", license = @License(name = "All Rights Reserved by google@2023")))

@SecurityScheme(name = "bearerAuth" , type = SecuritySchemeType.HTTP, bearerFormat = "JWT", scheme = "bearer")
public class OpenApiConfig {
	
	

}
