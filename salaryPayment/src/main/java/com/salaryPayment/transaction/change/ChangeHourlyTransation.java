package com.salaryPayment.transaction.change;

import com.salaryPayment.payment.classification.HourlyClassification;
import com.salaryPayment.payment.classification.PaymentClassification;
import com.salaryPayment.payment.schedule.PaymentSchedule;
import com.salaryPayment.payment.schedule.WeeklySchedule;

public class ChangeHourlyTransation extends ChangeClassificationTransation {
	private double hourlyRate;
	
	public ChangeHourlyTransation() {
		
	}
	
	public ChangeHourlyTransation(int empid, double hourlyRate) {
		super(empid);
		this.hourlyRate = hourlyRate;
	}

	@Override
	public PaymentClassification getClassification() {
		return new HourlyClassification(hourlyRate);
	}

	@Override
	public PaymentSchedule getSchedule() {
		return new WeeklySchedule();
	}

}
