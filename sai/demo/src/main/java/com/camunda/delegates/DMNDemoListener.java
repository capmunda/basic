package com.camunda.delegates;

import org.camunda.bpm.dmn.engine.DmnDecisionRuleResult;
import org.camunda.bpm.dmn.engine.DmnDecisionTableResult;
import org.camunda.bpm.engine.DecisionService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.ExecutionListener;
import org.camunda.bpm.engine.variable.VariableMap;
import org.camunda.bpm.engine.variable.Variables;

public class DMNDemoListener implements ExecutionListener {

	@Override
	public void notify(DelegateExecution execution) throws Exception {

		DecisionService ds = execution.getProcessEngineServices().getDecisionService();
		Object amount = execution.getVariable("amount");
		
		
		VariableMap variables = Variables.createVariables().putValue("amount", amount);

		DmnDecisionTableResult deb = ds.evaluateDecisionTableByKey("complementary", variables);
		
		DmnDecisionRuleResult result = deb.getSingleResult();
		
		System.out.println("starter "+result.get("starter"));
		System.out.println("voucher "+result.get("voucher"));
		
	}

}
