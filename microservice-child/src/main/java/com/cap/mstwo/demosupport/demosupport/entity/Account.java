package com.cap.mstwo.demosupport.demosupport.entity;

public class Account {
	private long amonut;

	public Account() {}

	public Account(long amonut) {
		super();
		this.amonut = amonut;
	}

	public long getAmonut() {
		return amonut;
	}

	public void setAmonut(long amonut) {
		this.amonut = amonut;
	}

	@Override
	public String toString() {
		return "Account [amonut=" + amonut + "]";
	}
	

}
