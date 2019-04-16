package com.salaryPayment.domain;

public class TimeCard {
	private long date;
	private double hours;
	
	public TimeCard() {
		
	}
	
	public TimeCard(long date, double hours) {
		this.date = date;
		this.hours = hours;
	}

	public long getDate() {
		return date;
	}

	public void setDate(long date) {
		this.date = date;
	}

	public double getHours() {
		return hours;
	}

	public void setHours(double hours) {
		this.hours = hours;
	}
	
	

}
