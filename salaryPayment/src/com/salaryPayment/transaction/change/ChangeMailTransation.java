package com.salaryPayment.transaction.change;

import com.salaryPayment.domain.MailMethod;
import com.salaryPayment.payment.method.PaymentMethod;

public class ChangeMailTransation extends ChangeMethodTransation {
	private String address;
	
	public ChangeMailTransation() {
		
	}
	
	public ChangeMailTransation(int empid, String address) {
		super(empid);
		this.address = address;
	}

	@Override
	public PaymentMethod getMethod() {
		return new MailMethod(address);
	}

}
