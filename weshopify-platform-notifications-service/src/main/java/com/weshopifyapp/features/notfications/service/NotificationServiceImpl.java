package com.weshopifyapp.features.notfications.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.weshopifyapp.features.commons.beans.NotificationsBean;
import com.weshopifyapp.features.notfications.outbound.MailJetSender;
import com.weshopifyapp.features.notfications.outbound.MailSender;

@Service
public class NotificationServiceImpl implements NotificationService {

	@Autowired
	private MailSender mailSender;
	
	@Autowired
	MailJetSender mailJetSender;

	
//	@Override
//	public NotificationsBean sendVerificationEmail(NotificationsBean notificationsBean) {
//
//		Map<String, Object> model = new HashMap<>();
//
//		return mailSender.sendVerificationEmail(notificationsBean, model);
//	}
	
	@Override
	public NotificationsBean sendVerificationEmail(NotificationsBean notificationsBean) {
		
		Map<String, Object> model = new HashMap<>();
		
		return mailJetSender.sendVerificationEmail(notificationsBean, model);
	}

}
