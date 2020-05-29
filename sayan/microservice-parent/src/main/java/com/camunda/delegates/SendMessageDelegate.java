package com.camunda.delegates;


import java.util.logging.Logger;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class SendMessageDelegate implements JavaDelegate {
	private final Logger LOGGER = Logger.getLogger(SendMessageDelegate.class.getName());
	DelegateExecution execution;
	@Override
	public void execute(DelegateExecution execution) throws Exception {
		
		LOGGER.info(execution.getBusinessKey());
		this.execution=execution;
		
		  String task=(String) execution.getVariable("editTask");
		  
		  execution.getProcessEngineServices().getRuntimeService().
		  createMessageCorrelation("EditTask").setVariable("editTask",
		  "how will u get the business key").correlate();	 	
	}
}
