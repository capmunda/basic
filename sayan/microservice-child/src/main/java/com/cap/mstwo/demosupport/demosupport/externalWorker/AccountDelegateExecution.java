package com.cap.mstwo.demosupport.demosupport.externalWorker;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import com.cap.mstwo.demosupport.demosupport.entity.Account;

public class AccountDelegateExecution implements JavaDelegate{
	Account account;
	long newAmount;
	long amount;
	public void doPrint() {
		ExternalTask ext=new ExternalTask();
		long amt=ext.getAmount();
		this.amount=ext.accountMap.get(1);
	}
		
	public void setAmount(long newAmount1) {
		 newAmount=newAmount1;	
	}
	@Override
	public void execute(DelegateExecution execution) throws Exception {
		
		execution.setVariable("amount",newAmount);
		
	}
}
