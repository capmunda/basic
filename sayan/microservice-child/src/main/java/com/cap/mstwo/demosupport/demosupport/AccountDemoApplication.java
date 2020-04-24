package com.cap.mstwo.demosupport.demosupport;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.cap.mstwo.demosupport.demosupport.externalWorker.ExternalTask;
import com.cap.mstwo.demosupport.demosupport.externalWorker.ExternalTaskDemo;

@SpringBootApplication
public class AccountDemoApplication {
	/*
	 * private final static Logger LOGGER =
	 * Logger.getLogger(AccountDemoApplication.class.getName());
	 */
	public static void main(String[] args) {
		
		SpringApplication.run(AccountDemoApplication.class, args);
		
		ExternalTask ext=new ExternalTask();
		ext.printAmount();
		
		ExternalTaskDemo extd=new ExternalTaskDemo();
		extd.addNumbers();
		extd.multiplynumbers();
		
		/*ExternalTaskClient client = ExternalTaskClient.create().baseUrl("http://localhost:8080/engine-rest")
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
			 amount=externalTask.getVariable("amount");
			LOGGER.info("the sum of  " +amount+" has been debited successfully at " +new Timestamp(new Date().getTime()));
			new AccountDemoApplication().setAmount(amount);
			externalTaskService.complete(externalTask);
		}).open();
		System.out.println(getAmount());
		
	}
	public static long getAmount() {
		return amount;
		
	}
	public  void setAmount(long amount) {
		this.amount=amount;
	}
*/
	}
}
