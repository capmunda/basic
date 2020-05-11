package com.cap.camunda.msone.demo.mailDemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.cap.camunda.msone.demo.utility.Keywords;
@Service
public class MailSenderClass {
	@Autowired
	private JavaMailSender javaMailSender;

	public void sendEmail() {
		System.out.println("Sending email...");
		SimpleMailMessage msg = new SimpleMailMessage();
		msg.setTo(Keywords.sendTo);

		msg.setSubject("Application Status");
		msg.setText("Please send your application id in below format \n"+Keywords.replyFormat);

		javaMailSender.send(msg);
	}
}
