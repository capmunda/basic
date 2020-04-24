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
	
	}
}
