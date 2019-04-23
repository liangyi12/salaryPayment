package com.salaryPayment.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.salaryPayment.database.PayrollDatabase;
import com.salaryPayment.domain.Employee;
import com.salaryPayment.transaction.add.AddHourlyEmployee;
import com.salaryPayment.transaction.change.ChangeNameTransation;

public class TestChangeNameTransation {
	PayrollDatabase gpayrollDatabase = PayrollDatabase.gpayrollDatabase;

	@Test
	public void payRollTest() throws Exception {
		int empid = 2;
		AddHourlyEmployee t = new AddHourlyEmployee(empid, "bob", "home", 12.3);
		t.excute();
		
		Employee e = gpayrollDatabase.getEmployee(empid);
		assertNotNull(e);
		
		ChangeNameTransation cnt = new ChangeNameTransation(empid, "bobo");
		cnt.excute();
		
		Employee e1 = gpayrollDatabase.getEmployee(empid);
		assertNotNull(e1);
		
		assertEquals("bobo", e1.getName());
	}

}
