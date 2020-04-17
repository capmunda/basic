package com.example.workflow;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class PrintBill implements JavaDelegate {

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		
		Long orderId = (Long) execution.getVariable("orderid");
		
		execution.getProcessEngineServices().getRuntimeService()
		.createMessageCorrelation("printorder")
		.setVariable("orderid", orderId)
		.correlate();
		
	}

}
