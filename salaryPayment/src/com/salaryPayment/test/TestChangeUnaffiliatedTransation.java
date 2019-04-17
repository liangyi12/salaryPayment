package com.salaryPayment.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.salaryPayment.affiliation.Affiliation;
import com.salaryPayment.affiliation.NoAffiliation;
import com.salaryPayment.affiliation.UnionAffiliation;
import com.salaryPayment.database.PayrollDatabase;
import com.salaryPayment.domain.Employee;
import com.salaryPayment.transaction.add.AddHourlyEmployee;
import com.salaryPayment.transaction.change.ChangeUnaffiliatedTransation;

public class TestChangeUnaffiliatedTransation {
	PayrollDatabase gPayrollDatabase = PayrollDatabase.gpayrollDatabase;

	@Test
	public void payRollTest() throws Exception {
		int empid = 2;
		int memberid = 22;
		AddHourlyEmployee t = new AddHourlyEmployee(empid, "test", "home", 33.3);
		t.excute();
		
		Employee e = gPayrollDatabase.getEmployee(empid);
		UnionAffiliation af = new UnionAffiliation(memberid, 25.5);
		e.setAffiliation(af);
		
		gPayrollDatabase.addUnionMember(memberid, e);
		
		ChangeUnaffiliatedTransation ct = new ChangeUnaffiliatedTransation(empid);
		ct.excute();
		
		e = gPayrollDatabase.getEmployee(empid);
		assertNotNull(e);
		Affiliation af1 = e.getAffiliation();
		assertNotNull(af1);
		assertTrue(af1 instanceof NoAffiliation);
		
		NoAffiliation nf = (NoAffiliation)af1;
		assertNotNull(nf);
	}

}
