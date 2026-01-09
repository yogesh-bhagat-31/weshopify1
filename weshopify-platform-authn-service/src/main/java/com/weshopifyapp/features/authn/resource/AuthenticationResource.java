package com.weshopifyapp.features.authn.resource;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.service.annotation.GetExchange;

import com.weshopifyapp.features.authn.bean.AuthenticationBean;
import com.weshopifyapp.features.authn.bean.Wso2TokenResponseBean;
import com.weshopifyapp.features.authn.service.WeshopifyAuthnService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class AuthenticationResource {

	WeshopifyAuthnService authnService; // Here AuthnResource class will wait till the spring bean creation of Weshopify authn service

	public AuthenticationResource(WeshopifyAuthnService authnService) {

		this.authnService = authnService;
	}

	@PostMapping("/token")
	public ResponseEntity<Wso2TokenResponseBean> authenticate(@RequestBody AuthenticationBean authnBean) {
		log.info("The user credentials submitted are:\t" + authnBean.toString());
		return ResponseEntity.ok(authnService.token(authnBean)) ;
	}

	@GetMapping("/token/logout")
	public ResponseEntity<Map<String, String>> logout(@RequestHeader("authorization") String accessTokenWithBearer){
		String accessTokenWithoutBearer = accessTokenWithBearer.replace("Bearer", "");
		log.info("accessTokenWithBearer is: " + accessTokenWithoutBearer);
		return ResponseEntity.ok(authnService.logout(accessTokenWithoutBearer));
	}
}
