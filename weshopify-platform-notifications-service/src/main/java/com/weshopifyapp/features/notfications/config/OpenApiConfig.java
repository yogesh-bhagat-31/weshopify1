package com.weshopifyapp.features.notfications.config;

import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;

@Configuration
@OpenAPIDefinition(info = @Info(title = "Notifications-Service",
                                description = "Notifications-Controller",
                                version = "1.0",
                                license = @License(name = "All Rights Reserved by weshopify@2023")))
public class OpenApiConfig {

}
