package com.salaryPayment.payment.classification;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.salaryPayment.domain.Paycheck;
import com.salaryPayment.domain.SalesReceipt;
import com.salaryPayment.util.DateUtil;

public class CommissionedClassification implements PaymentClassification {
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
		salesReceipts.put(sr.getDate().getTime(), sr);
	}

	public SalesReceipt getSalesReceipt(long date) {
		return salesReceipts.get(date);
	}

	@Override
	public double calculatePay(Paycheck pc) {
		double grossPay = this.salary;
		for (Entry<Long, SalesReceipt> entry: salesReceipts.entrySet()) {
			SalesReceipt sr = entry.getValue();
			if (DateUtil.isBetween(sr.getDate(), pc.getPayPeriodStartDate(), pc.getPayPeriodEndDate())) {
				grossPay += calculateForSalesReceipt(sr);
			}
		}
		return grossPay;
	}

	private double calculateForSalesReceipt(SalesReceipt sr) {
		return sr.getAmount() * commissionRate;
	}
}
