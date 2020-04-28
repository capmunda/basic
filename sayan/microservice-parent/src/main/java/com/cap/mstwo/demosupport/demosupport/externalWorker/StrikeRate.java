package com.cap.mstwo.demosupport.demosupport.externalWorker;

import java.util.HashMap;
import java.util.Map;

import org.camunda.bpm.client.ExternalTaskClient;
import org.camunda.bpm.client.topic.TopicSubscriptionBuilder;

public class StrikeRate {
	static  ExternalTaskClient client;
	double balls;
	double runs; 
	
	Map<String, Object> processVariables = new HashMap<>();
	static {
		client = ExternalTaskClient.create().baseUrl("http://localhost:8081/CamundaWorkflow/rest/engine/default")
				.asyncResponseTimeout(10000).disableBackoffStrategy().disableAutoFetching().
				  maxTasks(10).build();
	
	
	
	}
	public void countTestRuns() {
		TopicSubscriptionBuilder xmlSubscriptionBuilder=client.subscribe("test-strike-rate").lockDuration(1000) // the default lock duration is 20 seconds, but you can
		// override this
		.handler((externalTask, externalTaskService) -> {
			balls=(long)externalTask.getVariable("balls");
			runs=Math.ceil((balls*57.68)/100);
			processVariables.put("runs scored in that match", runs);
			externalTaskService.complete(externalTask,processVariables);
		});
		client.start();
		 xmlSubscriptionBuilder.open(); 
	}
	
	public void countT20Balls() {
		TopicSubscriptionBuilder xmlSubscriptionBuilder=client.subscribe("t20-strike-rate").lockDuration(1000) // the default lock duration is 20 seconds, but you can
				// override this
				.handler((externalTask, externalTaskService) -> {
					runs=(long)externalTask.getVariable("runs");
					balls=Math.ceil((runs/138.25)*100);
					processVariables.put("Balls played by Virat in that match", balls);
					externalTaskService.complete(externalTask,processVariables);
				});
				client.start();
				 xmlSubscriptionBuilder.open(); 
	}
	public void remainingRuns() {
		TopicSubscriptionBuilder xmlSubscriptionBuilder=client.subscribe("runs-remaining").lockDuration(1000) // the default lock duration is 20 seconds, but you can
				// override this
				.handler((externalTask, externalTaskService) -> {
					if(externalTask.getAllVariables().containsKey("runs")& externalTask.getAllVariables().containsKey("balls")) {
					runs=(long)externalTask.getVariable("runs");
					balls=(long)externalTask.getVariable("balls");
					double remainingruns=runs-30;
					double remainingBalls=balls-40;
					processVariables.put("Balls remaining", remainingBalls);
					processVariables.put("Runs remaining", remainingruns);					
					externalTaskService.complete(externalTask,processVariables);
					}
				});
				client.start();
				 xmlSubscriptionBuilder.open(); 
	}
	public void requiredRunrate() {
		TopicSubscriptionBuilder xmlSubscriptionBuilder=client.subscribe("required-runrate").lockDuration(1000) // the default lock duration is 20 seconds, but you can
				// override this
				.handler((externalTask, externalTaskService) -> {
					if(externalTask.getAllVariables().containsKey("Balls remaining") & externalTask.getAllVariables().containsKey("Runs remaining") ) {
					balls=externalTask.getVariable("Balls remaining");
					runs=externalTask.getVariable("Runs remaining");
					double rpo=(runs*6)/balls;
					
					processVariables.put("Required run rate per over", rpo);
					
					externalTaskService.complete(externalTask,processVariables);
					}
				});
				client.start();
				 xmlSubscriptionBuilder.open(); 
	}
}
