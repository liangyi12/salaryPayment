package com.salaryPayment.transaction.add;

import com.salaryPayment.payment.classification.CommissionedClassification;
import com.salaryPayment.payment.classification.PaymentClassification;
import com.salaryPayment.payment.schedule.BlweeklySchedule;
import com.salaryPayment.payment.schedule.PaymentSchedule;

public class AddCommissionedEmployee extends AddEmployeeTransaction {
	private double salary;
	private double commissionRate;
	
	public AddCommissionedEmployee() {
		
	}
	
	public AddCommissionedEmployee(int empid, String name, String address, double salary, double commissionRate) {
		super(empid, name, address);
		this.salary = salary;
		this.commissionRate = commissionRate;
	}

	@Override
	PaymentClassification getClassification() {
		return new CommissionedClassification(salary, commissionRate);
	}

	@Override
	PaymentSchedule getSchedule() {
		return new BlweeklySchedule();
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public double getCommissionRate() {
		return commissionRate;
	}

	public void setCommissionRate(double commissionRate) {
		this.commissionRate = commissionRate;
	}
	
	

}
