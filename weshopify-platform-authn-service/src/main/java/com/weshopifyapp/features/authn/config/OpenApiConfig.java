package com.weshopifyapp.features.authn.config;

import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;

@Configuration
@OpenAPIDefinition(info = @Info(title = "Authentication-Service",
                                description = "AuthnController",
                                version = "1.0",
                                license = @License(name = "All Rights Reserved by weshopify@2023")))
public class OpenApiConfig {

}
