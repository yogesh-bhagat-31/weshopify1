package com.weshopifyapp.features.notfications;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.ui.freemarker.FreeMarkerConfigurationFactoryBean;

@SpringBootApplication
public class WeshopifyPlatformNotificationsServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(WeshopifyPlatformNotificationsServiceApplication.class, args);
	}

	@Bean
	@Primary
	FreeMarkerConfigurationFactoryBean factoryBean() {
		FreeMarkerConfigurationFactoryBean factory = new FreeMarkerConfigurationFactoryBean();
		factory.setTemplateLoaderPath("classpath:/templates");
		return factory;

	}

}
