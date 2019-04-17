package com.salaryPayment.transaction.change;

import com.salaryPayment.affiliation.Affiliation;
import com.salaryPayment.affiliation.NoAffiliation;
import com.salaryPayment.affiliation.UnionAffiliation;
import com.salaryPayment.database.PayrollDatabase;
import com.salaryPayment.domain.Employee;

public class ChangeUnaffiliatedTransation extends ChangeAffiliationTransation {
	PayrollDatabase gPayrollDatabase = PayrollDatabase.gpayrollDatabase;
	
	public ChangeUnaffiliatedTransation() {
		
	}
	
	public ChangeUnaffiliatedTransation(int empid) {
		super(empid);
	}

	@Override
	public Affiliation getAffiliation() {
		return new NoAffiliation();
	}

	@Override
	public void recordMembership(Employee e) throws Exception {
		Affiliation af = e.getAffiliation();
		if(af instanceof UnionAffiliation) {
			int memberId = ((UnionAffiliation) af).getMemberId();
			gPayrollDatabase.removeUnionMember(memberId);
		}else {
			throw new Exception("tried to remove non-UnionAffiliation");
		}

	}

}
