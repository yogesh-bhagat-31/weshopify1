package com.weshopifyapp.features;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;

import reactor.core.publisher.Mono;


@SpringBootApplication
public class WeshopifyPlatformApiGatewayApplication {
	
	/**
	 * userKeyResolver() is used to provide a key for caching responses uniformly across all requests in your
	 * API Gateway, which can help in optimizing performance.
	 * 
	 * Caching: The method provides a static key "weshopify-app-cache" for all requests processed by the API Gateway. 
	 * This key can be used for caching responses in a distributed cache like Redis.
	 * 
	 * Uniform Key: Since every request gets the same key, it means that caching will be uniform, 
	 * and the same cache entry will be used for all requests.
	 * This is useful if you want a common cache for all requests handled by the API Gateway.
	 * @return
	 */
	KeyResolver userKeyResolver() {
		return exchager -> Mono.just("weshopify-app-cache");		
	}

	public static void main(String[] args) {
		
		SpringApplication.run(WeshopifyPlatformApiGatewayApplication.class, args);
		
	}

}
