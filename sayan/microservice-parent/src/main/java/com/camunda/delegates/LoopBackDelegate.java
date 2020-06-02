package com.camunda.delegates;

import java.util.Random;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class LoopBackDelegate implements JavaDelegate {

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		
		Random rd=new Random(); 
		  boolean loop=rd.nextBoolean();
		  System.out.println(loop); 
		  if(loop) {
			  execution.setVariable("loopback", true);
		  	}
		  else { 
			  execution.setVariable("loopback", false);
			  }
		
	}

}
