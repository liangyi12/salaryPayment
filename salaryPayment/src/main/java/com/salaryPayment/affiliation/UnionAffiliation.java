package com.salaryPayment.affiliation;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.salaryPayment.domain.Paycheck;
import com.salaryPayment.domain.ServiceCharge;
import com.salaryPayment.util.DateUtil;

public class UnionAffiliation implements Affiliation {
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
		serviceCharges.put(serviceCharge.getDate().getTime(), serviceCharge);
		
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

	@Override
	public double calculateDeductions(Paycheck pc) {
		double service_charges = 0;
		int numberOfFridayInPayPeriod = DateUtil.numberOfFridayBetweenTwoDate(pc.getPayPeriodStartDate(), pc.getPayPeriodEndDate());
		for (Entry<Long, ServiceCharge> entry : serviceCharges.entrySet()) {
			ServiceCharge sc = entry.getValue();
			if (DateUtil.isBetween(sc.getDate(), pc.getPayPeriodStartDate(), pc.getPayPeriodEndDate())) {
				service_charges += sc.getAmount();
			}
		}
		
		return dues * numberOfFridayInPayPeriod + service_charges;
	}
	
	
}
