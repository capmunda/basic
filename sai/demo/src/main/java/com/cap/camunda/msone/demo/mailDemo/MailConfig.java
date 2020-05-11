package com.cap.camunda.msone.demo.mailDemo;

import java.util.Properties;

import javax.mail.Flags;
import javax.mail.internet.MimeMessage;
import javax.mail.search.AndTerm;
import javax.mail.search.FlagTerm;
import javax.mail.search.SearchTerm;
import javax.mail.search.SubjectTerm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.MessageChannels;
import org.springframework.integration.mail.ImapMailReceiver;
import org.springframework.integration.mail.SearchTermStrategy;
import org.springframework.integration.mail.dsl.Mail;
import org.springframework.integration.mail.support.DefaultMailHeaderMapper;
import org.springframework.integration.mapping.HeaderMapper;
import org.springframework.integration.scheduling.PollerMetadata;
import org.springframework.scheduling.support.PeriodicTrigger;

import com.camunda.delegates.EmailTaskDelegate;
import com.cap.camunda.msone.demo.utility.Keywords;

@Configuration
@ConditionalOnProperty(
	    value="email.demo", 
	    havingValue = "true", 
	    matchIfMissing = false)
public class MailConfig {
	
	
	  @Bean public HeaderMapper<MimeMessage> mailHeaderMapper() { return new
	  DefaultMailHeaderMapper(); }
	 
	   @Bean
	    public ImapMailReceiver receiver()  {
	    	SearchTermStrategy searchTermStrategy = (supportedFlags, folder) -> {
	            SearchTerm search = new AndTerm(new SubjectTerm("*"+Keywords.subject+"*"),
	                    new FlagTerm(new Flags(Flags.Flag.SEEN), false));
	            return search;
	        };
	        
	        String loc = "imaps://" + Keywords.username +":" + Keywords.password + "@imap.gmail.com/INBOX";
	        ImapMailReceiver i = new ImapMailReceiver(loc);
	        i.setJavaMailProperties(javaMailProperties());
	        i.setSearchTermStrategy(searchTermStrategy);
	      i.setAutoCloseFolder(false);
	      i.setSimpleContent(true);
	        
	        //i.waitForNewMessages();
	        return i;
	    }
    @Bean
    public IntegrationFlow imapMailFlow() {    
    	

  
 
		/*
		 * IntegrationFlow flow = IntegrationFlows .from(Mail.imapInboundAdapter(
		 * "imaps://usr:pwd@imap.gmail.com/INBOX")
		 * .userFlag("testSIUserFlag"). javaMailProperties(javaMailProperties()), e ->
		 * e.autoStartup(true).poller(p -> p.fixedDelay(5000)))
		 * 
		 * .transform(Mail.toStringTransformer())
		 * .channel(MessageChannels.queue("imapChannel")).get();
		 */
		 
		 
		
		  IntegrationFlow flow = IntegrationFlows .from(Mail.imapIdleAdapter(receiver()))
		//.handle(System.out::println)
		.channel(MessageChannels.queue("imapChannel"))
		  .get();
		 
        return flow;
     }

		
		
	@Bean(name = PollerMetadata.DEFAULT_POLLER)
	public PollerMetadata defaultPoller() {

		PollerMetadata pollerMetadata = new PollerMetadata();
		pollerMetadata.setTrigger(new PeriodicTrigger(1000));
		//pollerMetadata.setMaxMessagesPerPoll(5);
		return pollerMetadata;
	}	 
		 
public Properties javaMailProperties() {
    Properties javaMailProperties = new Properties();

    javaMailProperties.setProperty("mail.imap.socketFactory.class","javax.net.ssl.SSLSocketFactory");
    javaMailProperties.setProperty("mail.imap.socketFactory.fallback","false");
    javaMailProperties.setProperty("mail.store.protocol","imaps");
    javaMailProperties.setProperty("mail.debug","false");

    return javaMailProperties;
	
}
//Spring Managed TAsk Delegate
@Bean
public EmailTaskDelegate emailTaskDelegate() {
	return new EmailTaskDelegate();
			
}
}
