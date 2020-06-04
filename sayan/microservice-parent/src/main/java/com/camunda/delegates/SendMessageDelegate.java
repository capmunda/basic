package com.camunda.delegates;


import java.util.logging.Logger;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class SendMessageDelegate implements JavaDelegate {
	private final Logger LOGGER = Logger.getLogger(SendMessageDelegate.class.getName());
	SearchingDelegate search=new SearchingDelegate();
	@Override
	public void execute(DelegateExecution execution) throws Exception {
		
		LOGGER.info(execution.getBusinessKey());
		String businessKey=execution.getBusinessKey();
		//System.out.println(businessKey);
		//System.out.println(search.childProceeBusinessKey);
		
		  String task=(String) execution.getVariable("editTask");
		  
		  execution.getProcessEngineServices().getRuntimeService().
		  createMessageCorrelation("EditTask").processInstanceBusinessKey(businessKey).setVariable("editTask",
		  "how will u get the business key").correlate();	 	
	}
}
