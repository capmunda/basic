package com.account.demo;

import java.sql.Timestamp;
import java.util.Date;
import java.util.logging.Logger;

import org.camunda.bpm.client.ExternalTaskClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AccountDemoApplication {
	private final static Logger LOGGER = Logger.getLogger(AccountDemoApplication.class.getName());
	public static void main(String[] args) {
		
		SpringApplication.run(AccountDemoApplication.class, args);
		ExternalTaskClient client = ExternalTaskClient.create().baseUrl("http://localhost:8080/engine-rest")
				.asyncResponseTimeout(10000) // long polling timeout
				.build();

		// subscribe to an external task topic as specified in the process
		client.subscribe("deposit-money").lockDuration(1000) // the default lock duration is 20 seconds, but you can
		// override this
		.handler((externalTask, externalTaskService) -> {
			long amount=externalTask.getVariable("amount");
			LOGGER.info("the sum of  " +amount+" has been credited successfully at "+ new Timestamp(new Date().getTime()));
			externalTaskService.complete(externalTask);
		}).open();
		
		client.subscribe("withdraw-money").lockDuration(1000) // the default lock duration is 20 seconds, but you can
		// override this
		.handler((externalTask, externalTaskService) -> {
			long amount=externalTask.getVariable("amount");
			LOGGER.info("the sum of  " +amount+" has been debited successfully at " +new Timestamp(new Date().getTime()));
			externalTaskService.complete(externalTask);
		}).open();
		
	}

}
