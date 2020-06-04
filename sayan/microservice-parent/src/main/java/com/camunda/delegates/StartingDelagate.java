package com.camunda.delegates;


import java.util.logging.Logger;

import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.ProcessEngines;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class StartingDelagate implements JavaDelegate {
	SendMessageDelegate send=new SendMessageDelegate();

	 private final Logger LOGGER = Logger.getLogger(StartingDelagate.class.getName());
	  
	  public void execute(DelegateExecution execution) throws Exception {
		  ProcessEngine processEngine=ProcessEngines.getDefaultProcessEngine();
		  
		 String childProceeBusinessKey="XYZ_"+execution.getBusinessKey();
		 
			 processEngine.getRuntimeService().startProcessInstanceByKey("child_process", childProceeBusinessKey);
		 	
	    LOGGER.info("\n\n  ... LoggerDelegate invoked by "
	            + "processDefinitionId=" + execution.getProcessDefinitionId()
	            + ", activtyId=" + execution.getCurrentActivityId()
	            + ", activtyName='" + execution.getCurrentActivityName() + "'"
	            + ", processInstanceId=" + execution.getProcessInstanceId()
	            + ", businessKey=" + execution.getProcessBusinessKey()
	            + ", executionId=" + execution.getId()
	            + ", tenantId=" + execution.getTenantId()
	            + ", \n\n");
	//    System.out.println(processEngine.getRepositoryService());
	    
	  }

	}
