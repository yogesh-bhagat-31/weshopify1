package com.weshopifyapp.features.brands.resource;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.weshopifyapp.features.brands.bean.BrandsBean;
import com.weshopifyapp.features.brands.service.BrandsService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class BrandsResource {

	private BrandsService brandsService;

	public BrandsResource(BrandsService brandsService) {
		super();
		this.brandsService = brandsService;
	}

	
	@PostMapping(value = { "/brands" })
//	@CircuitBreaker(name = "create_brands_circuit", fallbackMethod = "findCategoryByIDFallBack")
//	@Retry(name = "create_brands_retry" , fallbackMethod = "findCategoryByIDFallBack")
//	@RateLimiter(name = "create_brands_ratelimiter", fallbackMethod = "limitExceededFallBack")
	@Operation(summary ="createBrand", security = @SecurityRequirement(name = "bearerAuth"))
	public ResponseEntity<BrandsBean> createBrand(@RequestBody BrandsBean brandsBean) {
        log.info("Creating the brand by input data:\t" +brandsBean.toString());
		return ResponseEntity.ok(brandsService.createBrand(brandsBean));

	}

	public ResponseEntity<Object> findCategoryByIDFallBack(Throwable th) {
		// Here We will go to secondary/disaster/backup category services but
		// right now we will pass message form map object
		Map<String, String> map = new HashMap<>();
		map.put("erroCode", String.valueOf(HttpStatus.SERVICE_UNAVAILABLE.value()));
		map.put("message", "Categories Service is down at this moment. It will be available soon!!");
		return ResponseEntity.ok(map);

	}

	public ResponseEntity<Object> limitExceededFallBack(Throwable th) {
		// Here We will go to secondary/disaster/backup category services but
		// right now we will pass message form map object
		Map<String, String> map = new HashMap<>();
		map.put("erroCode", String.valueOf(HttpStatus.TOO_MANY_REQUESTS.value()));
		map.put("message", "Threyshold limit reached please retry after some time");
		return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).body(map);

	}

	@PutMapping(value = { "/brands" })
	@Operation(summary ="updateBrand", security = @SecurityRequirement(name = "bearerAuth"))
	public ResponseEntity<BrandsBean> updateBrand(@RequestBody BrandsBean brandsBean) {

		return ResponseEntity.ok(brandsService.updateBrand(brandsBean));

	}

	@GetMapping(value = { "/brands" })
	@Operation(summary ="findAllBrands", security = @SecurityRequirement(name = "bearerAuth"))
	public ResponseEntity<List<BrandsBean>> findAllBrands() {

		return ResponseEntity.ok(brandsService.findAllBrands());
	}

	@GetMapping(value = { "/brands/{brandsId}" })
	@Operation(summary ="findBrandById", security = @SecurityRequirement(name = "bearerAuth"))
	public ResponseEntity<BrandsBean> findBrandById(@PathVariable("brandsId") String id) {

		return ResponseEntity.status(HttpStatus.FOUND.value()).body(brandsService.findBrandById(id));
	}

	@GetMapping(value = { "/brands/cleanDb" })
	@Operation(summary ="cleanBrandsDb", security = @SecurityRequirement(name = "bearerAuth"))
	public ResponseEntity<Map<String, String>> cleanBrandsDb() {
		brandsService.cleanDb();
		Map<String, String> map = new HashMap<>();
		map.put("message", "All Documents Deleted From MongoDb");
		return ResponseEntity.ok(map);
	}

	@DeleteMapping(value = { "/brands/{brandsId}" })
	@Operation(summary ="deleteBrandById", security = @SecurityRequirement(name = "bearerAuth"))
	public ResponseEntity<List<BrandsBean>> deleteBrandById(@PathVariable("brandsId") String id) {

		return ResponseEntity.status(HttpStatus.OK.value()).body(brandsService.deleteBrand(id));
	}

}
