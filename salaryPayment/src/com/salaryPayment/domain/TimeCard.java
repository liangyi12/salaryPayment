package com.salaryPayment.domain;

import java.util.Date;

public class TimeCard {
	private Date date;
	private double hours;
	
	public TimeCard() {
		
	}
	
	public TimeCard(Date date, double hours) {
		this.date = date;
		this.hours = hours;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public double getHours() {
		return hours;
	}

	public void setHours(double hours) {
		this.hours = hours;
	}
	
	

}
