package com.salaryPayment.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.salaryPayment.affiliation.Affiliation;
import com.salaryPayment.affiliation.UnionAffiliation;
import com.salaryPayment.database.PayrollDatabase;
import com.salaryPayment.domain.Employee;
import com.salaryPayment.transaction.add.AddHourlyEmployee;
import com.salaryPayment.transaction.change.ChangeMemberTransation;

public class TestChangeMemberTransation {
	PayrollDatabase gPayrollDatabase = PayrollDatabase.gpayrollDatabase;

	@Test
	public void payRollTest() throws Exception {
		int empid = 2;
		int memberid = 7734;
		AddHourlyEmployee t = new AddHourlyEmployee(empid, "test", "home", 33.3);
		t.excute();
		
		ChangeMemberTransation cmt = new ChangeMemberTransation(empid, memberid, 12.4);
		cmt.excute();
		
		Employee e = gPayrollDatabase.getEmployee(empid);
		assertNotNull(e);
		Affiliation af1 = e.getAffiliation();
		assertNotNull(af1);
		assertTrue(af1 instanceof UnionAffiliation);
		
		UnionAffiliation uf = (UnionAffiliation)af1;
		assertNotNull(uf);
		
		assertEquals(12.4, uf.getDues(), .01);
		
		Employee e1 = gPayrollDatabase.getUnionMember(memberid);
		assertNotNull(e1);
		assertTrue(e == e1);
	}

}
