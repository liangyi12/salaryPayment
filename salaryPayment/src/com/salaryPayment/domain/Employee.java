package com.salaryPayment.domain;

import com.salaryPayment.payment.classification.PaymentClassification;
import com.salaryPayment.payment.method.PaymentMethod;
import com.salaryPayment.payment.schedule.PaymentSchedule;

public class Employee {
	private int itsEmpid;
	private String itsName;
	private String itsAddress;
	private PaymentClassification classification;
	private PaymentSchedule schedule;
	private PaymentMethod method;
	private Affiliation affiliation;

	public Employee(int itsEmpid, String itsName, String itsAddress) {
		this.itsEmpid = itsEmpid;
		this.itsName = itsName;
		this.itsAddress = itsAddress;
	}

	public int getItsEmpid() {
		return itsEmpid;
	}

	public void setItsEmpid(int itsEmpid) {
		this.itsEmpid = itsEmpid;
	}

	public String getItsName() {
		return itsName;
	}

	public void setItsName(String itsName) {
		this.itsName = itsName;
	}

	public String getItsAddress() {
		return itsAddress;
	}

	public void setItsAddress(String itsAddress) {
		this.itsAddress = itsAddress;
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

	

}
