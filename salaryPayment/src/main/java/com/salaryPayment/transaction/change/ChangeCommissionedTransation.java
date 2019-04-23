package com.salaryPayment.transaction.change;

import com.salaryPayment.payment.classification.CommissionedClassification;
import com.salaryPayment.payment.classification.PaymentClassification;
import com.salaryPayment.payment.schedule.BlweeklySchedule;
import com.salaryPayment.payment.schedule.PaymentSchedule;

public class ChangeCommissionedTransation extends ChangeClassificationTransation {
	private double salary;
	private double commissionRate;
	
	public ChangeCommissionedTransation() {
		
	}
	
	public ChangeCommissionedTransation(int empid, double salary, double commissionRate) {
		super(empid);
		this.salary = salary;
		this.commissionRate = commissionRate;
	}

	@Override
	public PaymentClassification getClassification() {
		return new CommissionedClassification(salary, commissionRate);
	}

	@Override
	public PaymentSchedule getSchedule() {
		return new BlweeklySchedule();
	}

}
