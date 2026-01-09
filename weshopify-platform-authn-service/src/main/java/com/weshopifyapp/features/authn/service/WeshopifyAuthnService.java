package com.weshopifyapp.features.authn.service;

import java.util.Map;

import com.weshopifyapp.features.authn.bean.AuthenticationBean;
import com.weshopifyapp.features.authn.bean.Wso2TokenResponseBean;

public interface WeshopifyAuthnService {

	Wso2TokenResponseBean token(AuthenticationBean authnBean);
	
	Map<String,String> logout(String accessToken);

}
