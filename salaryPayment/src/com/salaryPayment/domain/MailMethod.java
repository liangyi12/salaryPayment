package com.salaryPayment.domain;

import com.salaryPayment.payment.method.PaymentMethod;

public class MailMethod extends PaymentMethod {
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
	
	
}
