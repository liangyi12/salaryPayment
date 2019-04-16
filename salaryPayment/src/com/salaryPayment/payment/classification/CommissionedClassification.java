package com.salaryPayment.payment.classification;

import java.util.HashMap;
import java.util.Map;

import com.salaryPayment.domain.SalesReceipt;

public class CommissionedClassification extends PaymentClassification {
	private double salary;
	private double commissionRate;
	
	private Map<Long, SalesReceipt> salesReceipts = new HashMap<Long, SalesReceipt>();
	
	public CommissionedClassification() {
		
	}
	
	public CommissionedClassification(double salary, double commissionRate) {
		this.salary = salary;
		this.commissionRate = commissionRate;
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

	public void addSalesReceipt(SalesReceipt sr) {
		salesReceipts.put(sr.getDate(), sr);
	}

	public SalesReceipt getSalesReceipt(long date) {
		return salesReceipts.get(date);
	}
	
}
