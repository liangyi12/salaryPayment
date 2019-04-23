package com.salaryPayment.domain;

import java.util.Date;

public class SalesReceipt {
	private Date date;
	private int amount;
	
	public SalesReceipt() {
		
	}

	public SalesReceipt(Date date, int amount) {
		super();
		this.date = date;
		this.amount = amount;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}	
	
}
