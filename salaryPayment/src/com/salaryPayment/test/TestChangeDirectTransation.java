package com.salaryPayment.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.salaryPayment.database.PayrollDatabase;
import com.salaryPayment.domain.Employee;
import com.salaryPayment.payment.method.DirectMethod;
import com.salaryPayment.payment.method.PaymentMethod;
import com.salaryPayment.transaction.add.AddHourlyEmployee;
import com.salaryPayment.transaction.change.ChangeDirectTransation;

public class TestChangeDirectTransation {
	PayrollDatabase gpayrollDatabase = PayrollDatabase.gpayrollDatabase;

	@Test
	public void payRollTest() throws Exception {
		int empid = 2;
		AddHourlyEmployee t = new AddHourlyEmployee(empid, "test", "home", 33.3);
		t.excute();
		
		Employee e = gpayrollDatabase.getEmployee(empid);
		assertNotNull(e);
		
		ChangeDirectTransation ct = new ChangeDirectTransation(empid, "jianhang", "100100");
		ct.excute();
		
		e = gpayrollDatabase.getEmployee(empid);
		assertNotNull(e);
		PaymentMethod pm = e.getMethod();
		assertTrue(pm instanceof DirectMethod);
		
		DirectMethod dm = (DirectMethod)pm;
		assertNotNull(dm);
		assertEquals("jianhang", dm.getBank());
		assertEquals("100100", dm.getAccount());
	}

}
