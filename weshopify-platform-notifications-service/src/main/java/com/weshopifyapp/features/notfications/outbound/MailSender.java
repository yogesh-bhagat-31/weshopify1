package com.weshopifyapp.features.notfications.outbound;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.objects.Email;
import com.weshopifyapp.features.commons.beans.EmailRequest;
import com.weshopifyapp.features.commons.beans.NotificationsBean;

import freemarker.template.Configuration;
import freemarker.template.Template;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class MailSender {

	@Autowired
	private Configuration freeMarkerConfig;

	@Value("${sendgrid.api.key}")
	private String sendGridApiKey;

	@Value("${sendgrid.api.from}")
	private String sendgridFrom;
	
	@Value("${sendgrid.api.verifyEmail}")
	private String verificationEmailLink;

	public NotificationsBean sendVerificationEmail(NotificationsBean notificationsData, Map<String, Object> model) {

		try {

			List<EmailRequest> emailRequests = notificationsData.getTo();
			for (EmailRequest er : emailRequests) {
				model.put("receiver", er.getEmail());
				model.put("verifyEmailLink", verificationEmailLink + er.getEmail());
				Template template = freeMarkerConfig.getTemplate("signup_email.ftl");
				String templateWithData = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);
				String sendMailTo = er.getEmail();
				Email from = new Email(sendgridFrom);
//				String subject = notificationsData.getSubject();
				Email to = new Email(sendMailTo);
//				Content content = new Content(notificationsData.getContentType(), templateWithData);
//				Mail mail = new Mail(from, subject, to, content);

				SendGrid sg = new SendGrid(System.getenv(sendGridApiKey));
				Request request = new Request();

				request.setMethod(Method.POST);
				request.setEndpoint("mail/send");
//				request.setBody(mail.build());
				Response response = sg.api(request);
				System.out.println(response.getStatusCode());
				System.out.println(response.getBody());
				System.out.println(response.getHeaders());
//				notificationsData.setStatusCode(response.getStatusCode());

			}

		} catch (Exception e) {

			log.error(e.getLocalizedMessage());

		}

		return notificationsData;

	}

}
