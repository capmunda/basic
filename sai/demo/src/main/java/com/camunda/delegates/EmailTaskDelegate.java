package com.camunda.delegates;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.DelegateTask;
import org.camunda.bpm.engine.delegate.TaskListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;

import com.cap.camunda.msone.demo.mailDemo.CustomSpringEvent;
import com.cap.camunda.msone.demo.mailDemo.MailSenderClass;
import com.cap.camunda.msone.demo.utility.Keywords;

public class EmailTaskDelegate implements TaskListener, ApplicationListener<CustomSpringEvent> {
	
	private volatile boolean mailReceived =false;
	
	private volatile Long applicationId;
	@Autowired
	MailSenderClass mailSender;

	@Override
	public void notify(DelegateTask delegateTask) {
		mailSender.sendEmail();
		while(!mailReceived){
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				
			}
		}
		
		DelegateExecution execution = delegateTask.getExecution();
		execution.setVariable("appId", this.applicationId);
		//execution.getProcessEngine().getTaskService().complete(delegateTask.getId());
		
	}
	
	@Override
    public void onApplicationEvent(CustomSpringEvent event) {
        System.out.println("Received spring custom event - ");
        String message = event.getMessage();
        Pattern pattern = Pattern.compile("(?<="+Keywords.replyFormat+")\\d+");
        Matcher matcher = pattern.matcher(message);
        String appId = null;
        if (matcher.find()) {
            System.out.println("Matcher "+matcher.group(0));
           appId=matcher.group(0);
        }
    
	System.out.println("App id"+ appId);
         this.applicationId = Long.parseLong(appId);
        this.mailReceived = true;
    }

}
