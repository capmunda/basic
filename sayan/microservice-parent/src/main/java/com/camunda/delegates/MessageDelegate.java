package com.camunda.delegates;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class MessageDelegate implements JavaDelegate{

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		String question=(String)execution.getVariable("question");
		execution.getProcessEngineServices().getRuntimeService().createMessageCorrelation("AskGoogle").setVariable("question", question).correlate();
		
	}

}
