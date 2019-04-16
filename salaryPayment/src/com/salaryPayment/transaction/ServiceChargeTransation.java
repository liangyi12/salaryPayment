package com.salaryPayment.transaction;

import com.salaryPayment.database.PayrollDatabase;
import com.salaryPayment.domain.Affiliation;
import com.salaryPayment.domain.Employee;
import com.salaryPayment.domain.ServiceCharge;
import com.salaryPayment.domain.UnionAffiliation;

public class ServiceChargeTransation implements Transaction {
	private int itsMemberId;
	private long itsDate;
	private double itsCharge;
	
	PayrollDatabase gpayrollDatabase = PayrollDatabase.gpayrollDatabase;
	
	public ServiceChargeTransation() {
		
	}
	
	public ServiceChargeTransation(int memberId, long date, double charge) {
		itsMemberId = memberId;
		itsDate = date;
		itsCharge = charge;
	}

	@Override
	public void excute() throws Exception {
		Employee e = gpayrollDatabase.getUnionMember(itsMemberId);
		if (e != null) {
			Affiliation af = e.getAffiliation();
			if (af != null) {
				if(af instanceof UnionAffiliation) {
					UnionAffiliation uaf = (UnionAffiliation)af;
					uaf.addServiceCharge(new ServiceCharge(itsDate, itsCharge));
				}else{
					throw new Exception("tried to add serviceCharge to non-unionAffiliation");
				}
			}else{
				throw new Exception("there is no affiliation in employee" + e);
			}
		}else{
			throw new Exception("no such employee with memberid " + itsMemberId);
		}
	}

}
