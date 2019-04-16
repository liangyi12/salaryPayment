package com.salaryPayment.domain;

public class SalesReceipt {
	private long date;
	private int amount;
	
	public SalesReceipt() {
		
	}
	
	public SalesReceipt(long date, int amount) {
		this.date = date;
		this.amount = amount;
	}

	public long getDate() {
		return date;
	}

	public void setDate(long date) {
		this.date = date;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}
	
	
}
