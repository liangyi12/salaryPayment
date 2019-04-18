package com.salaryPayment.domain;

import java.util.Date;

public class ServiceCharge {
	private Date date;
	private double amount;
	
	public ServiceCharge() {
		
	}
	
	public ServiceCharge(Date date, double amount) {
		this.date = date;
		this.amount = amount;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}
	
	
}
