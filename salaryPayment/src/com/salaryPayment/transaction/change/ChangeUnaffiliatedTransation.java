package com.salaryPayment.transaction.change;

import com.salaryPayment.database.PayrollDatabase;
import com.salaryPayment.domain.Affiliation;
import com.salaryPayment.domain.Employee;
import com.salaryPayment.domain.NoAffiliation;
import com.salaryPayment.domain.UnionAffiliation;

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
