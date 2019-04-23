package com.salaryPayment.payment.classification;

import com.salaryPayment.domain.Paycheck;


public class SalariedClassification implements PaymentClassification {
	private double salary;

	public SalariedClassification(double itsSalary) {
		salary = itsSalary;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	@Override
	public double calculatePay(Paycheck pc) {
		return salary;
	}
	
	

}
