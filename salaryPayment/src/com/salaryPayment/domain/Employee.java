package com.salaryPayment.domain;

import java.util.Date;

import com.salaryPayment.affiliation.Affiliation;
import com.salaryPayment.payment.classification.PaymentClassification;
import com.salaryPayment.payment.method.PaymentMethod;
import com.salaryPayment.payment.schedule.PaymentSchedule;

public class Employee {
	private int empid;
	private String name;
	private String address;
	private PaymentClassification classification;
	private PaymentSchedule schedule;
	private PaymentMethod method;
	private Affiliation affiliation;

	public Employee(int itsEmpid, String itsName, String itsAddress) {
		this.empid = itsEmpid;
		this.name = itsName;
		this.address = itsAddress;
	}

	public int getEmpid() {
		return empid;
	}

	public void setEmpid(int itsEmpid) {
		this.empid = itsEmpid;
	}

	public String getName() {
		return name;
	}

	public void setName(String itsName) {
		this.name = itsName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String itsAddress) {
		this.address = itsAddress;
	}

	public PaymentClassification getClassification() {
		return classification;
	}

	public void setClassification(PaymentClassification classification) {
		this.classification = classification;
	}

	public PaymentSchedule getSchedule() {
		return schedule;
	}

	public void setSchedule(PaymentSchedule schedule) {
		this.schedule = schedule;
	}

	public PaymentMethod getMethod() {
		return method;
	}

	public void setMethod(PaymentMethod method) {
		this.method = method;
	}

	public Affiliation getAffiliation() {
		return affiliation;
	}
	
	public void setAffiliation(Affiliation affiliation) {
		this.affiliation = affiliation;
	}

	public boolean isPayDate(Date payDate) {
		return schedule.isPayDate(payDate);
	}

	public void payDay(Paycheck pc) {
		double grossPay = classification.calculatePay(pc);
		double deductions = affiliation.calculateDeductions(pc);
		double netpay = grossPay - deductions;
		pc.setGrossPay(grossPay);
		pc.setDeductions(deductions);
		pc.setNetPay(netpay);
		
		method.pay(pc);
	}

	public Date getPayPeriodStartDate(Date payDate) {
		return schedule.getPayPeriodStartDate(payDate);
	}

	

}
