package com.salaryPayment.transaction;

import com.salaryPayment.payment.classification.PaymentClassification;
import com.salaryPayment.payment.classification.SalariedClassification;
import com.salaryPayment.payment.schedule.MonthlySchedule;
import com.salaryPayment.payment.schedule.PaymentSchedule;

public class AddSalariedEmployee extends AddEmployeeTransaction{
	private double itsSalary;
	
	public AddSalariedEmployee() {
		
	}
	
	public AddSalariedEmployee(int empid, String name, String address, double salary) {
		super(empid, name, address);
		this.itsSalary = salary;
	}

	@Override
	PaymentClassification getClassification() {
		return new SalariedClassification(itsSalary);
	}

	@Override
	PaymentSchedule getSchedule() {
		return new MonthlySchedule();
	}

	public double getItsSalary() {
		return itsSalary;
	}

	public void setItsSalary(double itsSalary) {
		this.itsSalary = itsSalary;
	}
	
	

}
