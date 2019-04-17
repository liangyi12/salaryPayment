package com.salaryPayment.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.salaryPayment.database.PayrollDatabase;
import com.salaryPayment.domain.Employee;
import com.salaryPayment.payment.method.MailMethod;
import com.salaryPayment.payment.method.PaymentMethod;
import com.salaryPayment.transaction.add.AddHourlyEmployee;
import com.salaryPayment.transaction.change.ChangeMailTransation;

public class TestChangeMailTransation {
	PayrollDatabase gpayrollDatabase = PayrollDatabase.gpayrollDatabase;

	@Test
	public void payRollTest() throws Exception {
		int empid = 2;
		AddHourlyEmployee t = new AddHourlyEmployee(empid, "test", "home", 33.3);
		t.excute();
		
		Employee e = gpayrollDatabase.getEmployee(empid);
		assertNotNull(e);
		
		ChangeMailTransation ct = new ChangeMailTransation(empid, "xxlu");
		ct.excute();
		
		e = gpayrollDatabase.getEmployee(empid);
		assertNotNull(e);
		
		PaymentMethod pm = e.getMethod();
		assertTrue(pm instanceof MailMethod);
		
		MailMethod mm = (MailMethod)pm;
		assertEquals("xxlu", mm.getAddress());
	}

}
