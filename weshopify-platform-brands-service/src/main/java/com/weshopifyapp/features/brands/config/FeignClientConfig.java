package com.weshopifyapp.features.brands.config;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//@FeignClient(name = "weshopify-platform-categories-service", url = "localhost:5016")
@FeignClient(name = "WESHOPIFY-PLATFORM-CATEGORIES-SERVICE")
//@FeignClient(name = "WESHOPIFY-PLATFORM-GATEWAY")
//gateway-categories-service
public interface FeignClientConfig {

	@GetMapping("/categories/{catId}")
	public ResponseEntity<String> getCategoriesById(@PathVariable("catId") int catId);

}
