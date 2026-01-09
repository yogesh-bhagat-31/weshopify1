package com.weshopifyapp.features.authn.clients;

import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.Base64;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.weshopifyapp.features.authn.bean.Wso2TokenRequestBean;
import com.weshopifyapp.features.authn.bean.Wso2TokenResponseBean;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class Wso2IamClient {

	@Autowired
	ObjectMapper mapper;

	@Value("${iam.wso2.grant_type}")
	private String grantType; // SPEL we emebeded values from yml file

	@Value("${iam.wso2.scope}")
	private String scope;

	@Value("${iam.wso2.iam-token-uri}")
	private String iamTokenUri;

	@Value("${iam.wso2.client-id}")
	private String clientId;

	@Value("${iam.wso2.client-secret}")
	private String clientSecret;

	private RestTemplate restTemplate;

	public Wso2IamClient(RestTemplate restTemplate) {

		this.restTemplate = restTemplate;
	}

	public Wso2TokenResponseBean getAuthnToken(String username, String password) {

		Wso2TokenRequestBean tokenReqBean = Wso2TokenRequestBean // HTTP request ki body prepare karli
				.builder().grant_type(grantType).scope(scope).username(username).password(password).build();

		byte[] encodedCreds = Base64.getEncoder().encode((clientId + ":" + clientSecret).getBytes());// Here we encoded
																										// client id and
																										// client secret

		HttpHeaders headers = new HttpHeaders(); // HTTp Request Headers ki value set kari
		headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
		headers.add(HttpHeaders.AUTHORIZATION, "Basic " + new String(encodedCreds));

		try {

			String authnData = mapper.writeValueAsString(tokenReqBean);

			HttpEntity requestData = new HttpEntity(authnData, headers); // Finally we prepared the request data

//			String tokenUri = iamBaseUri + "/oauth2/token"; 

			log.info("Ignoring the Certificates in Dev Env");
			ignoreCertificates();
			System.out.println("iam token uri is:\t" + iamTokenUri);
//			ResponseEntity<String> response = restTemplate.postForEntity(iamTokenUri, requestData, String.class); // Yahe pe finally hum call karnege WSO2 ke API ko
			ResponseEntity<String> response = restTemplate.exchange(iamTokenUri, HttpMethod.POST, requestData,
					String.class);

			if (response != null && response.getStatusCode().value() == HttpStatus.OK.value()) {
				log.info("response data is:\t" + response.getBody());
				return mapper.readValue(response.getBody(), Wso2TokenResponseBean.class);
			} else {
				throw new RuntimeException(response.getBody());

			}
		} catch (Exception e) {
			log.error(e.getLocalizedMessage());
			throw new RuntimeException(e.getLocalizedMessage());
		}

	}

	// Here we ignored the SSL cert
	private void ignoreCertificates() {

		TrustManager[] trustAllCerts = new TrustManager[] {

				new X509TrustManager() {
					@Override
					public X509Certificate[] getAcceptedIssuers() {
						return null;
					}

					@Override
					public void checkClientTrusted(X509Certificate[] certs, String authType) {
					}

					@Override
					public void checkServerTrusted(X509Certificate[] certs, String authType) {
					}
				} };

		try {
			SSLContext sc = SSLContext.getInstance("TLS");
			sc.init(null, trustAllCerts, new SecureRandom());
			HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
		} catch (Exception e) {

		}

	}

}
