package com.weshopifyapp.features.users.clients;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.weshopifyapp.features.commons.beans.NotificationsBean;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class NotificationServiceRestClient {

	@Autowired
	private RestTemplate restTemplate;

	@Value("${mailJet.serviceUrl}")
	private String notificationServiceUrl;

	public NotificationsBean sendVerificationEmail(NotificationsBean notificationsBean) {

		HttpHeaders headers = new HttpHeaders();
		headers.set("Content-Type", MediaType.APPLICATION_JSON_VALUE); // content type we have set to jason

		HttpEntity request = new HttpEntity(notificationsBean, headers); // We created HTTP request Object

		ResponseEntity<NotificationsBean> response = restTemplate.postForEntity(notificationServiceUrl, request,
				NotificationsBean.class);

		if (response != null && response.getStatusCode().is2xxSuccessful()) {
			// Send reset password link through the email
			log.info("email verified successfully and sending the password reset link");

		}
		return response.getBody();
	}

}
