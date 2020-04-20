package com.cap.mstwo.demosupport.demosupport.externalWorker;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.camunda.bpm.client.ExternalTaskClient;

import com.cap.mstwo.demosupport.demosupport.entity.Account;

public class ExternalTask {
	static  ExternalTaskClient client;
	long amount;
	 Account account=new Account(amount);
	 AccountDelegateExecution acde=new AccountDelegateExecution();
	 Map<Integer, Long> accountMap=new HashMap<>();
	 
	static {
		client = ExternalTaskClient.create().baseUrl("http://localhost:8080/engine-rest")
				.asyncResponseTimeout(10000) // long polling timeout
				.build();
	
	client.start();
	
	}
	public void printAmount() {
	// subscribe to an external task topic as specified in the process
	client.subscribe("deposit-money").lockDuration(1000) // the default lock duration is 20 seconds, but you can
	// override this
	.handler((externalTask, externalTaskService) -> {
		 amount=externalTask.getVariable("amount");
		System.out.println(("the sum of  " +amount+" has been credited successfully at "+ new Timestamp(new Date().getTime())));
		externalTaskService.complete(externalTask);
	}).open();
	
	client.subscribe("withdraw-money").lockDuration(1000) // the default lock duration is 20 seconds, but you can
	// override this
	.handler((externalTask, externalTaskService) -> {
		 amount=externalTask.getVariable("amount");
		 System.out.println("the sum of  " +amount+" has been debited successfully at " +new Timestamp(new Date().getTime()));
		 account.setAmonut(amount);
		 acde.setAmount(amount);
		 accountMap.put(1, account.getAmonut());
		 System.out.println(accountMap);
		 externalTaskService.complete(externalTask);
	}).open();
	
	}

public  long getAmount() {
	return amount;
	
}
public  long setAmount(long amount) {
	return this.amount=amount;
}

}

