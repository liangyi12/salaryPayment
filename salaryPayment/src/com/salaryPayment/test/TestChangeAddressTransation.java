package com.salaryPayment.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.salaryPayment.database.PayrollDatabase;
import com.salaryPayment.domain.Employee;
import com.salaryPayment.transaction.add.AddHourlyEmployee;
import com.salaryPayment.transaction.change.ChangeAddressTransation;

public class TestChangeAddressTransation {
	PayrollDatabase gpayrollDatabase = PayrollDatabase.gpayrollDatabase;

	@Test
	public void payRollTest() throws Exception {
		int empid = 2;
		AddHourlyEmployee t = new AddHourlyEmployee(empid, "bob", "home", 12.3);
		t.excute();
		
		Employee e = gpayrollDatabase.getEmployee(empid);
		assertNotNull(e);
		
		ChangeAddressTransation cnt = new ChangeAddressTransation(empid, "home1");
		cnt.excute();
		
		Employee e1 = gpayrollDatabase.getEmployee(empid);
		assertNotNull(e1);
		
		assertEquals("home1", e1.getAddress());
	}

}
