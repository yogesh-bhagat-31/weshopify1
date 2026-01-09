package com.weshopifyapp.features.notfications;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.weshopifyapp.features.commons.beans.EmailRequest;
import com.weshopifyapp.features.commons.beans.NotificationsBean;
import com.weshopifyapp.features.notfications.service.NotificationService;

@SpringBootTest
class WeshopifyPlatformNotificationsServiceApplicationTests {

	@Autowired
	NotificationService notificationService;

	@Test
	void contextLoads() {
	}

//	@Test
//	public void sendVerificationEmail() {
//
//		NotificationsBean notification = new NotificationsBean();
//		notification.setFrom("yogeshkumar3111994@gmail.com");
//
//		List<EmailRequest> toList = new ArrayList<>();
//
//		EmailRequest toEmail = new EmailRequest();
//		toEmail.setEmail("bhagat.yogesh.b@gmail.com");
//		toList.add(toEmail);
//
//		notification.setTo(toList);
//		notification.setContentType(MediaType.TEXT_HTML_VALUE);
//		notification.setSubject("Welcome To WeshopifyApp Platform");
//
//		notificationService.sendVerificationEmail(notification);
//	}

	@Test
	public void sendVerificationEmail() {

		NotificationsBean notification = new NotificationsBean();
		
		List<EmailRequest> toList = new ArrayList<>();

		EmailRequest toEmail1 = new EmailRequest();
		EmailRequest toEmail2 = new EmailRequest();
		toEmail1.setEmail("bhagat.yogesh.b@gmail.com");
		toList.add(toEmail1);
		toEmail2.setEmail("yogeshkumar3111994@gmail.com");
		toList.add(toEmail2);
		notification.setTo(toList);
		notificationService.sendVerificationEmail(notification);
	}

}
