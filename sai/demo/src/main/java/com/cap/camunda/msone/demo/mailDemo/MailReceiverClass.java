package com.cap.camunda.msone.demo.mailDemo;

import javax.mail.internet.MimeMessage;

import org.apache.logging.log4j.util.Strings;
import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.ProcessEngines;
import org.camunda.bpm.engine.RuntimeService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
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
						System.out.println(sub);
                    	String processDefinitionKey = sub.replace("Trigger workflow ", "");
						System.out.println(processDefinitionKey);
						
						if(Strings.isNotBlank(processDefinitionKey)) {
						startProcess(processDefinitionKey);
						}
						
					} catch (javax.mail.MessagingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
                    //System.out.println(getTextFromMessage(mailMessage));
	            	
	            }

				// Processing emails do with them something..

				/*
				 * for (EmailAction emailAction : services) { emailAction.performAction(null); }
				 */
			}
		};
		return messageHandler;
	}

	
	public  void startProcess(String processDefinitionKey) {
		
		ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
		
		RuntimeService runtimeService = processEngine.getRuntimeService();
		runtimeService.startProcessInstanceByKey(processDefinitionKey);
		
	}
}
