package com.salaryPayment.payment.classification;

import com.salaryPayment.domain.Paycheck;

public interface PaymentClassification {

	public double calculatePay(Paycheck pc);

}
