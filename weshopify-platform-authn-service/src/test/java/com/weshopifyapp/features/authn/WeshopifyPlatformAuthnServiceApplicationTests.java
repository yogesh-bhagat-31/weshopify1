package com.weshopifyapp.features.authn;

import java.security.SecureRandom;
import java.security.cert.X509Certificate;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.weshopifyapp.features.authn.bean.AuthenticationBean;
import com.weshopifyapp.features.authn.clients.Wso2IamClient;
import com.weshopifyapp.features.authn.service.WeshopifyAuthnService;

@SpringBootTest
class WeshopifyPlatformAuthnServiceApplicationTests {

	@Test
	void contextLoads() {
	}

	@Autowired
	private Wso2IamClient iamClient;

	@Autowired
	private WeshopifyAuthnService authnService;

	@Test
	public void testToken() {

//		iamClient.getAuthnToken("Yogesh_Rock", "@SY9894rock#");
		authnService.token(AuthenticationBean.builder().userName("Yogesh_Rock").password("@SY9894rock#").build());
	}

}
