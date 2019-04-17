package com.salaryPayment.transaction.change;

import com.salaryPayment.payment.method.HoldMethod;
import com.salaryPayment.payment.method.PaymentMethod;

public class ChangeHoldTransation extends ChangeMethodTransation {
	
	public ChangeHoldTransation() {
		
	}
	
	public ChangeHoldTransation(int empid) {
		super(empid);
	}

	@Override
	public PaymentMethod getMethod() {
		return new HoldMethod();
	}

	

}
