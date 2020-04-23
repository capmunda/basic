package com.camunda.delegates;

import org.camunda.bpm.dmn.engine.DmnDecisionTableResult;
import org.camunda.bpm.engine.DecisionService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.ExecutionListener;
import org.camunda.bpm.engine.dmn.DecisionEvaluationBuilder;
import org.camunda.bpm.engine.variable.VariableMap;
import org.camunda.bpm.engine.variable.Variables;

public class MyDecisionResultListener  implements ExecutionListener {

	@Override
	public void notify(DelegateExecution execution) throws Exception {
		
		  DecisionService ds =
		  execution.getProcessEngineServices().getDecisionService();
		  
		  VariableMap variables = Variables.createVariables() .putValue("beverage",
		  "juice");
		  
		  
		  DmnDecisionTableResult deb =
		  ds.evaluateDecisionTableByKey("DecideBeverage",variables);
		  //DmnDecisionTableResult der = deb.evaluate(); Object result =
		  deb.getSingleEntry();
		 
		
		/*
		 * DecisionService ds =
		 * execution.getProcessEngineServices().getDecisionService();
		 * 
		 * //VariableMap variables = Variables.createVariables() .putValue("beverage",
		 * "juice");
		 * 
		 * 
		 * DecisionEvaluationBuilder deb =
		 * ds.evaluateDecisionTableByKey("DecideBeverage"); DmnDecisionTableResult der =
		 * deb.evaluate(); Object result = der.getSingleEntry();
		 */
	}

}
