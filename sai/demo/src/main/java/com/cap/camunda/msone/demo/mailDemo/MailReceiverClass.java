package com.cap.camunda.msone.demo.mailDemo;

import java.io.IOException;

import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.ProcessEngines;
import org.camunda.bpm.engine.RuntimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessagingException;
import org.springframework.stereotype.Service;

@Service
@ConditionalOnProperty(
	    value="email.demo", 
	    havingValue = "true", 
	    matchIfMissing = false)
public class MailReceiverClass {
	// private List<EmailAction> services;

	@Autowired
    private ApplicationEventPublisher applicationEventPublisher;
	
	@Bean

	@ServiceActivator(inputChannel = "imapChannel")
	public MessageHandler processNewEmail() {
		MessageHandler messageHandler = new MessageHandler() {

			@Override
			public void handleMessage(org.springframework.messaging.Message<?> message) throws MessagingException {
				System.out.println("New email:" + message.toString());
				
				Object payload = message.getPayload();
				
	            if (payload instanceof MimeMessage) {
	            	javax.mail.Message mailMessage = (javax.mail.Message) payload;
                    try {
                    	String sub = mailMessage.getSubject();
						//System.out.println(sub);
						/*
						 * String processDefinitionKey = sub.replace("Trigger workflow ", "");
						 * System.out.println(processDefinitionKey);
						 * 
						 * if(Strings.isNotBlank(processDefinitionKey)) {
						 * startProcess(processDefinitionKey); }
						 */
						String applicationId = getTextFromMessage(mailMessage);
						 System.out.println(applicationId);
						 
						 System.out.println("Publishing custom event. ");
						 CustomSpringEvent customSpringEvent = new CustomSpringEvent(this, applicationId);
						 applicationEventPublisher.publishEvent(customSpringEvent);
						
					} catch (javax.mail.MessagingException | IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
                   
	            	
	            }

				// Processing emails do with them something..

				/*
				 * for (EmailAction emailAction : services) { emailAction.performAction(null); }
				 */
			}
		};
		return messageHandler;
	}

	
	private String getTextFromMessage(Message message) throws  IOException, javax.mail.MessagingException {
	    String result = "";
	    if (message.isMimeType("text/plain")) {
	        result = message.getContent().toString();
	    } else if (message.isMimeType("multipart/*")) {
	        MimeMultipart mimeMultipart = (MimeMultipart) message.getContent();
	        result = getTextFromMimeMultipart(mimeMultipart);
	    }
	    return result;
	}

	private String getTextFromMimeMultipart(
	        MimeMultipart mimeMultipart)  throws  IOException, javax.mail.MessagingException{
	    String result = "";
	    int count = mimeMultipart.getCount();
	    for (int i = 0; i < count; i++) {
	        BodyPart bodyPart = mimeMultipart.getBodyPart(i);
	        if (bodyPart.isMimeType("text/plain")) {
	            result = result + "\n" + bodyPart.getContent();
	            break; // without break same text appears twice in my tests
	        } else if (bodyPart.isMimeType("text/html")) {
	            String html = (String) bodyPart.getContent();
	            result = result + "\n" + org.jsoup.Jsoup.parse(html).text();
	        } else if (bodyPart.getContent() instanceof MimeMultipart){
	            result = result + getTextFromMimeMultipart((MimeMultipart)bodyPart.getContent());
	        }
	    }
	    return result;
	}
	
	
	public  void startProcess(String processDefinitionKey) {
		
		ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
		
		RuntimeService runtimeService = processEngine.getRuntimeService();
		runtimeService.startProcessInstanceByKey(processDefinitionKey);
		
	}
}
