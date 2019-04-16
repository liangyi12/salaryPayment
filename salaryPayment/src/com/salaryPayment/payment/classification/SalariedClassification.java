package com.salaryPayment.payment.classification;


public class SalariedClassification extends PaymentClassification {
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
	
	

}
