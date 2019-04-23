package com.salaryPayment.payment.method;

import com.salaryPayment.domain.Paycheck;


public class DirectMethod implements PaymentMethod {
	private String bank;
	private String account;
	
	public DirectMethod() {
		
	}

	public DirectMethod(String bank, String account) {
		super();
		this.bank = bank;
		this.account = account;
	}

	public String getBank() {
		return bank;
	}

	public void setBank(String bank) {
		this.bank = bank;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	@Override
	public void pay(Paycheck pc) {
		// TODO Auto-generated method stub
		
	}
	
	
	
	
}
