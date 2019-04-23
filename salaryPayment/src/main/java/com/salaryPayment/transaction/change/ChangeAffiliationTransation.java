package com.salaryPayment.transaction.change;

import com.salaryPayment.affiliation.Affiliation;
import com.salaryPayment.domain.Employee;

public abstract class ChangeAffiliationTransation extends ChangeEmployeeTransation{
	
	public ChangeAffiliationTransation() {
		
	}
	
	public ChangeAffiliationTransation(int empid) {
		super(empid);
	}

	@Override
	public void change(Employee e) throws Exception {
		recordMembership(e);
		Affiliation af = getAffiliation();
		e.setAffiliation(af);
		
	}
	
	public abstract Affiliation getAffiliation();
	
	public abstract void recordMembership(Employee e) throws Exception;

}
