package com.weshopifyapp.features.notfications.outbound;

import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import com.mailjet.client.ClientOptions;
import com.mailjet.client.MailjetClient;
import com.mailjet.client.MailjetRequest;
import com.mailjet.client.MailjetResponse;
import com.mailjet.client.resource.Emailv31;
import com.weshopifyapp.features.commons.beans.EmailRequest;
import com.weshopifyapp.features.commons.beans.NotificationsBean;

import freemarker.template.Configuration;
import freemarker.template.Template;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class MailJetSender {
	@Autowired
	private Configuration freeMarkerConfig;

	@Value("${mailJet.api.key}")
	private String mailJetApiKey;
	
	@Value("${mailJet.api.secretkey}")
	private String mailJetSecretKey;

	@Value("${mailJet.api.from}")
	private String mailJetFrom;
	
	@Value("${mailJet.api.subject}")
	private String mailJetSubject;
	
	@Value("${sendgrid.api.verifyEmail}")
	private String verificationEmailLink;
	public NotificationsBean sendVerificationEmail(NotificationsBean notificationsData, Map<String, Object> model){
		
		try {
			for( EmailRequest er : notificationsData.getTo()) {
				model.put("receiver", er.getEmail());
				model.put("verifyEmailLink", verificationEmailLink + er.getId());
				Template template = freeMarkerConfig.getTemplate("signup_email.ftl");
				String templateWithData = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);
				 MailjetClient client;
				    MailjetRequest request;
				    MailjetResponse response;
				    client = new MailjetClient(mailJetApiKey, mailJetSecretKey, new ClientOptions("v3.1"));
				    System.out.println(client.toString());
				    
				    
				    request = new MailjetRequest(Emailv31.resource)
				    					        .property(Emailv31.MESSAGES, new JSONArray()
				                                               .put(new JSONObject()
				                                            		   .put(Emailv31.Message.FROM, new JSONObject()
				                                            				   		.put("Email", mailJetFrom)
				                                            				   		.put("Name", "Yogesh"))
				                                            		   				.put(Emailv31.Message.TO, new JSONArray()
				                                            		   												.put(new JSONObject()
				                                            		   															.put("Email", er.getEmail())
				                                            		   															.put("Name", "Yogesh")))
				                                               .put(Emailv31.Message.SUBJECT, mailJetSubject)
				                                               .put(Emailv31.Message.TEXTPART, "My first Mailjet email")
				                                               .put(Emailv31.Message.HTMLPART, templateWithData)
				                                               .put(Emailv31.Message.CUSTOMID, "AppGettingStartedTest")));
				    
				    
				    response = client.post(request);
				    System.out.println(response.getStatus());
				    System.out.println(response.getData());
				    notificationsData.setStatusCode(response.getStatus());
			}
		} catch (Exception e) {
			log.error(e.getLocalizedMessage()) ;
		}
         
                  
         return notificationsData;
	}
	
}
