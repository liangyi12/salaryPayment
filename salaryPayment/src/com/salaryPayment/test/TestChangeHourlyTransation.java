package com.salaryPayment.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.salaryPayment.database.PayrollDatabase;
import com.salaryPayment.domain.Employee;
import com.salaryPayment.payment.classification.HourlyClassification;
import com.salaryPayment.payment.classification.PaymentClassification;
import com.salaryPayment.payment.schedule.PaymentSchedule;
import com.salaryPayment.payment.schedule.WeeklySchedule;
import com.salaryPayment.transaction.add.AddCommissionedEmployee;
import com.salaryPayment.transaction.change.ChangeHourlyTransation;

public class TestChangeHourlyTransation {
	PayrollDatabase gpayrollDatabase = PayrollDatabase.gpayrollDatabase;

	@Test
	public void payRollTest() throws Exception {
		int empid = 2;
		AddCommissionedEmployee t = new AddCommissionedEmployee(empid, "bob", "home", 1000.0, 55.5);
		t.excute();
		
		Employee e = gpayrollDatabase.getEmployee(empid);
		assertNotNull(e);
		
		ChangeHourlyTransation cht = new ChangeHourlyTransation(empid, 66.6);
		cht.excute();
		
		Employee e1 = gpayrollDatabase.getEmployee(empid);
		assertNotNull(e1);
		
		PaymentClassification pc = e1.getClassification();
		HourlyClassification hc = (HourlyClassification)pc;
		assertNotNull(hc);
		assertEquals(66.6, hc.getHourlyRate(), .01);
		
		PaymentSchedule ps = e1.getSchedule();
		WeeklySchedule ws = (WeeklySchedule)ps;
		assertNotNull(ws);
		
	}

}
