package com.camunda.delegates;

import java.util.logging.Logger;

import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.ProcessEngines;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class SearchingDelegate implements JavaDelegate{
	
	@Override
	public void execute(DelegateExecution execution) throws Exception {
		
		  ProcessEngine processEngine=ProcessEngines.getDefaultProcessEngine();
		  
			 String childProceeBusinessKey="XYZ_"+execution.getProcessInstanceId();
			 
			 if(processEngine.getRuntimeService().createProcessInstanceQuery().processInstanceBusinessKey(childProceeBusinessKey).count()>0) {
				 execution.setVariable("Task", "editTask");
			 }
			 else {
				 execution.setVariable("Task", "createTask");
			}
		
	}

}
