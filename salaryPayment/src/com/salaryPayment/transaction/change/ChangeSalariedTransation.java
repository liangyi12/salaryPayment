package com.salaryPayment.transaction.change;

import com.salaryPayment.payment.classification.PaymentClassification;
import com.salaryPayment.payment.classification.SalariedClassification;
import com.salaryPayment.payment.schedule.MonthlySchedule;
import com.salaryPayment.payment.schedule.PaymentSchedule;

public class ChangeSalariedTransation extends ChangeClassificationTransation {
	private double salary;
	
	public ChangeSalariedTransation() {
		
	}
	
	public ChangeSalariedTransation(int empid, double salary) {
		super(empid);
		this.salary = salary;
	}

	@Override
	public PaymentClassification getClassification() {
		return new SalariedClassification(salary);
	}

	@Override
	public PaymentSchedule getSchedule() {	
		return new MonthlySchedule();
	}

}
