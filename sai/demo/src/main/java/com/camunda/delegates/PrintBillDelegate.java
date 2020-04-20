package com.camunda.delegates;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class PrintBillDelegate implements JavaDelegate {
	//message throw-catch to spin child process
	//BPMN printorder_start.bpmn(parent) printorder_Messagedelegate.bpmn(child)
	@Override
	public void execute(DelegateExecution execution) throws Exception {
		
		Long orderId = (Long) execution.getVariable("orderid");
		
		execution.getProcessEngineServices().getRuntimeService()
		.createMessageCorrelation("printorder")
		.setVariable("orderid", orderId)
		.correlate();
		
	}

}
