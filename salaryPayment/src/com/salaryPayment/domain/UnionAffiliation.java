package com.salaryPayment.domain;

import java.util.HashMap;
import java.util.Map;

public class UnionAffiliation extends Affiliation {
	private int memberId;
	private double dues;
	
	private Map<Long, ServiceCharge> serviceCharges = new HashMap<Long, ServiceCharge>();
	
	public UnionAffiliation() {
		
	}
	
	public UnionAffiliation(int memberId, double dues) {
		this.memberId = memberId;
		this.dues = dues;
	}

	public void addServiceCharge(ServiceCharge serviceCharge) {
		serviceCharges.put(serviceCharge.getDate(), serviceCharge);
		
	}

	public ServiceCharge getServiceCharge(long date) {
		return serviceCharges.get(date);
	}

	public int getMemberId() {
		return memberId;
	}

	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}

	public double getDues() {
		return dues;
	}

	public void setDues(double dues) {
		this.dues = dues;
	}
	
	
}
