package com.camunda.delegates;


import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.ProcessEngines;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class SearchingDelegate implements JavaDelegate{
	String childProceeBusinessKey;
	
	@Override
	public void execute(DelegateExecution execution) throws Exception {
		
		  ProcessEngine processEngine=ProcessEngines.getDefaultProcessEngine();
		  
			 String childProceeBusinessKey="XYZ_"+execution.getBusinessKey();
			 this.childProceeBusinessKey=childProceeBusinessKey;
			 if(processEngine.getRuntimeService().createProcessInstanceQuery().processInstanceBusinessKey(childProceeBusinessKey).count()>0) {
				 execution.setVariable("Task", "editTask");
				 execution.setProcessBusinessKey(childProceeBusinessKey);
			 }
			 else {
				 execution.setVariable("Task", "createTask");
			}
		
	}

}
