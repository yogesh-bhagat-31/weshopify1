package com.weshopifyapp.features.categories.config;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpRequest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserAuthzService {

	@Autowired
	private RedisTemplate<String, String> redisTemplate;

	public Authentication authorizeToken(HttpServletRequest httpRequest) {
		String token = resolveToken(httpRequest);
		if (token != null && !token.isEmpty()) {
			HashOperations<String, String, String> operations = redisTemplate.opsForHash();
			String tokenExpiry = operations.get("tokenExpiry", token.trim());

			boolean isTokenValid = isTokenValid(tokenExpiry);

			/**
			 * If above token is valid preapare authn object Get user details
			 */

			if (isTokenValid) {
				String subject = null;
				String profileJason = operations.get(token, token);
//				if (profileJason != null && !profileJason.isEmpty()) {
//					// Add org.jason dependency
//					JSONObject jason = new JSONObject(profileJason);
//					if(jason.has("sub"))
//					subject = jason.getString("sub"); // Even we can get the other properties like role
//
//				}
				Authentication authentication = new UsernamePasswordAuthenticationToken(profileJason, null,
						Arrays.asList(new SimpleGrantedAuthority("admin")));
				return authentication;
			}
		}

		return null;

	}

	private String resolveToken(HttpServletRequest httpRequest) {		
		String tokenWithBearer = httpRequest.getHeader("Authorization");
		if(tokenWithBearer != null && !tokenWithBearer.isEmpty()) {
			log.info("Token with bearer is: " + tokenWithBearer);
			String token = tokenWithBearer.replace("Bearer", "");
			log.info("Token without bearer is: " + token);
			return token.trim();	
		}
		return "";
	}

	private boolean isTokenValid(String tokenExpiry) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.SECOND,Integer.valueOf(tokenExpiry));
		Date tokenExpiryDate = calendar.getTime();
		Date currentDate = new Date();
		if (tokenExpiryDate.before(currentDate)) {
			return false;
		} else {
			return true;
		}
		
		
	}

}
