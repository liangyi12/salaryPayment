package com.salaryPayment.transaction;

import com.salaryPayment.payment.classification.HourlyClassification;
import com.salaryPayment.payment.classification.PaymentClassification;
import com.salaryPayment.payment.schedule.PaymentSchedule;
import com.salaryPayment.payment.schedule.WeeklySchedule;

public class AddHourlyEmployee  extends AddEmployeeTransaction{
	private double hourlyRate;
	
	public AddHourlyEmployee() {
		
	}
	
	public AddHourlyEmployee(int empid, String name, String address, double hourlyRate) {
		super(empid, name, address);
		this.hourlyRate = hourlyRate;
	}

	public double getHourlyRate() {
		return hourlyRate;
	}

	public void setHourlyRate(double hourlyRate) {
		this.hourlyRate = hourlyRate;
	}

	@Override
	PaymentClassification getClassification() {
		return new HourlyClassification(hourlyRate);
	}

	@Override
	PaymentSchedule getSchedule() {
		return new WeeklySchedule();
	}

}
