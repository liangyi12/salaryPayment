package com.salaryPayment.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.salaryPayment.database.PayrollDatabase;
import com.salaryPayment.domain.Employee;
import com.salaryPayment.domain.ServiceCharge;
import com.salaryPayment.domain.UnionAffiliation;
import com.salaryPayment.transaction.AddHourlyEmployee;
import com.salaryPayment.transaction.ServiceChargeTransation;

public class TestAddServiceCharge {
	PayrollDatabase gpayrollDatabase = PayrollDatabase.gpayrollDatabase;
	
	@Test
	public void payRollTest() throws Exception {
		int empid = 2;
		int memberid = 22;
		AddHourlyEmployee t = new AddHourlyEmployee(empid, "test", "home", 25.5);
		t.excute();
		
		Employee e = gpayrollDatabase.getEmployee(empid);
		assertNotNull(e);
		
		UnionAffiliation af = new UnionAffiliation(memberid, 25.5);
		e.setAffiliation(af);
		
		gpayrollDatabase.addUnionMember(memberid, e);
		
		ServiceChargeTransation st = new ServiceChargeTransation(memberid, 12333, 12.95);
		st.excute();
		
		ServiceCharge sc = af.getServiceCharge(12333);
		assertNotNull(sc);
		assertEquals(12.95, sc.getAmount(), .01);
		
	}

}
