package com.salaryPayment.payment.method;

import com.salaryPayment.domain.Paycheck;


public class MailMethod implements PaymentMethod {
	private String address;
	
	public MailMethod() {
		
	}
	
	public MailMethod(String address) {
		this.address = address;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public void pay(Paycheck pc) {
		
		
	}
	
	
}
