package com.weshopifyapp.features.authn.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.json.JSONObject;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.stereotype.Service;

import com.weshopifyapp.features.authn.bean.AuthenticationBean;
import com.weshopifyapp.features.authn.bean.Wso2TokenResponseBean;
import com.weshopifyapp.features.authn.clients.Wso2IamClient;

import io.micrometer.common.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import netscape.javascript.JSObject;

@Service
@Slf4j
public class WeshopifyAuthnImpl implements WeshopifyAuthnService {

	Wso2IamClient wso2IamClient;

	RedisTemplate<String, String> redisTemplate;
	
	HashOperations<String, String, String> cacheStorage;

	public WeshopifyAuthnImpl(Wso2IamClient wso2IamClient, RedisTemplate<String, String> redisTemplate) {
		this.wso2IamClient = wso2IamClient;
		this.redisTemplate = redisTemplate;
		cacheStorage = redisTemplate.opsForHash();
	}

	@Override
	public Wso2TokenResponseBean token(AuthenticationBean authnBean) {
		Wso2TokenResponseBean tokenResponse = wso2IamClient.getAuthnToken(authnBean.getUserName(), authnBean.getPassword());
		
        String accessToken = Optional.ofNullable(tokenResponse)
        		.filter(tokenBean-> StringUtils.isNotEmpty(tokenBean.getAccess_token()))
        		.map(tokenBean-> tokenBean.getAccess_token())
        		.get();
        
        log.info("Acees token is:/t" + accessToken);
        
    	JSONObject json = new JSONObject(tokenResponse);
        String access_token = json.getString("access_token");
        log.info("access_token is:/t" + access_token);
        int expiryOfToken = json.getInt("expires_in");
        /**
         * make a getUserInfo call(we are calling the Wso2 APi) and retrieve the logged in user profile
         * and take the email id anf put it in cache
         */
//        String expiry = String.valueOf(tokenResponse.getExpires_in());
//        String jsonString = "{\"sub\":\"" + expiry + "\"}";
        if(!accessToken.isEmpty()) {
        	String randomHash = authnBean.getUserName()+"_"+RandomStringUtils.random(512);
        	log.info("Token hash is: " + randomHash);
        	 cacheStorage.put(access_token, access_token,  "admin@Wso2.com" );
             cacheStorage.put( "tokenExpiry", access_token, String.valueOf(expiryOfToken));
             log.info("the value stored in cache for the access token key is : ", cacheStorage.get(accessToken, accessToken));
        	
        }
       
        return tokenResponse;
	}

	@Override
	public Map<String, String> logout(String accessToken) {
		String loggedUser = cacheStorage.get( "tokenExpiry", accessToken);// This method call is not working for me
		cacheStorage.delete(loggedUser, accessToken);
		cacheStorage.delete(accessToken, accessToken);
		Map<String, String> map = new HashMap<>();
		map.put(loggedUser, "has been logged out successfully");
		return map;
	}

}
