package com.cap.mstwo.demosupport.demosupport.externalWorker;

import java.util.HashMap;
import java.util.Map;

import org.camunda.bpm.client.ExternalTaskClient;
import org.camunda.bpm.client.topic.TopicSubscriptionBuilder;

public class ExternalTaskDemo {
	static  ExternalTaskClient client;
	long number1;
	long number2;
	Map<String, Object> processVariables = new HashMap<>();
	static {
		client = ExternalTaskClient.create().baseUrl("http://localhost:8081/CamundaWorkflow/rest/engine/default")
				.asyncResponseTimeout(10000).disableBackoffStrategy().disableAutoFetching().
				  maxTasks(10).build();
	
	
	
	}
	public void addNumbers() {
		TopicSubscriptionBuilder xmlSubscriptionBuilder=client.subscribe("add-number").lockDuration(1000) // the default lock duration is 20 seconds, but you can
		// override this
		.handler((externalTask, externalTaskService) -> {
			long number1=externalTask.getVariable("number1");
			long number2=externalTask.getVariable("number2");
			long total=number1+number2;
			processVariables.put("total", total);
			externalTaskService.complete(externalTask,processVariables);
		});
		client.start();
		 xmlSubscriptionBuilder.open(); 
	}
	public void multiplynumbers() {
		TopicSubscriptionBuilder xmlSubscriptionBuilder1=client.subscribe("miltiplie-total").lockDuration(1000)
		.handler((externalTask, externalTaskService) -> {
			if(externalTask.getAllVariables().containsKey("total")) {		
			long total1=externalTask.getVariable("total");
			long multiplied=total1*5;
			processVariables.put("The multiplied value is ", multiplied);
			externalTaskService.complete(externalTask,processVariables);
			}
		});
		client.start();
		 xmlSubscriptionBuilder1.open(); 
	}
}
