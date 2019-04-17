package com.salaryPayment.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.salaryPayment.database.PayrollDatabase;
import com.salaryPayment.domain.Employee;
import com.salaryPayment.domain.TimeCard;
import com.salaryPayment.payment.classification.HourlyClassification;
import com.salaryPayment.payment.classification.PaymentClassification;
import com.salaryPayment.transaction.add.AddHourlyEmployee;
import com.salaryPayment.transaction.add.AddTimeCardTransation;

public class TestAddTimeCardTransation {
	PayrollDatabase gpayrollDatabase = PayrollDatabase.gpayrollDatabase;

	@Test
	public void payRolltest() throws Exception {
		int empid = 2;
		long date = 12333;
		AddHourlyEmployee t = new AddHourlyEmployee(empid, "test", "home", 55.5);
		t.excute();
		
		AddTimeCardTransation tct = new AddTimeCardTransation(date, 8.0, empid);
		tct.excute();
		
		Employee e = gpayrollDatabase.getEmployee(empid);
		assertNotNull(e);
		
		PaymentClassification pc = e.getClassification();
		HourlyClassification hc = (HourlyClassification)pc;
		assertNotNull(hc);
		
		TimeCard tc = hc.getTimeCard(date);
		assertNotNull(tc);
		assertEquals(8.0, tc.getHours(), .01);
		
		
		
		
	}

}
