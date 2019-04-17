package com.salaryPayment.transaction.change;

import com.salaryPayment.database.PayrollDatabase;
import com.salaryPayment.domain.Affiliation;
import com.salaryPayment.domain.Employee;
import com.salaryPayment.domain.UnionAffiliation;

public class ChangeMemberTransation extends ChangeAffiliationTransation {
	private int memberId;
	private double dues;
	
	PayrollDatabase gPayrollDatabase = PayrollDatabase.gpayrollDatabase;
	
	public ChangeMemberTransation() {
		
	}
	
	public ChangeMemberTransation(int empid, int memberId, double dues) {
		super(empid);
		this.memberId = memberId;
		this.dues = dues;
	}

	@Override
	public Affiliation getAffiliation() {
		return new UnionAffiliation(memberId, dues);
	}

	@Override
	public void recordMembership(Employee e) {
		gPayrollDatabase.addUnionMember(memberId, e);
		
	}

}
