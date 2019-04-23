package com.salaryPayment.payment.method;

import com.salaryPayment.domain.Paycheck;

public interface PaymentMethod {

	public void pay(Paycheck pc);
}
