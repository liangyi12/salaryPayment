package com.salaryPayment.transaction.change;

import com.salaryPayment.payment.method.DirectMethod;
import com.salaryPayment.payment.method.PaymentMethod;

public class ChangeDirectTransation extends ChangeMethodTransation {
	private String bank;
	private String account;
	
	public ChangeDirectTransation() {
		
	}
	
	public ChangeDirectTransation(int empid, String bank, String account) {
		super(empid);
		this.bank = bank;
		this.account = account;
		
	}


	@Override
	public PaymentMethod getMethod() {
		return new DirectMethod(bank, account);
	}

}
