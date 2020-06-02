package com.camunda.delegates;

import org.camunda.bpm.engine.delegate.DelegateTask;
import org.camunda.bpm.engine.delegate.TaskListener;

public class TestListener implements TaskListener {

	@Override
	public void notify(DelegateTask delegateTask) {
		 delegateTask.setAssignee("say");
		    delegateTask.addCandidateUser("guest");
		
	}

}
