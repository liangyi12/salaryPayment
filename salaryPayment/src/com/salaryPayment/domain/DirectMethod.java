package com.salaryPayment.domain;

import com.salaryPayment.payment.method.PaymentMethod;

public class DirectMethod extends PaymentMethod {
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
	
	
	
	
}
