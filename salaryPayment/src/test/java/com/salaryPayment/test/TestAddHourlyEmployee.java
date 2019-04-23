package com.salaryPayment.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.salaryPayment.database.PayrollDatabase;
import com.salaryPayment.domain.Employee;
import com.salaryPayment.payment.classification.HourlyClassification;
import com.salaryPayment.payment.classification.PaymentClassification;
import com.salaryPayment.payment.method.HoldMethod;
import com.salaryPayment.payment.method.PaymentMethod;
import com.salaryPayment.payment.schedule.PaymentSchedule;
import com.salaryPayment.payment.schedule.WeeklySchedule;
import com.salaryPayment.transaction.add.AddHourlyEmployee;

public class TestAddHourlyEmployee {
	PayrollDatabase gpayrollDatabase = PayrollDatabase.gpayrollDatabase;

	@Test
	public void payrollTest() {
		int empId = 2;
		
		AddHourlyEmployee t = new AddHourlyEmployee(empId, "Bob", "home", 55.5);
		t.excute();
		
		Employee e = gpayrollDatabase.getEmployee(empId);
		assertNotNull(e);
		
		PaymentClassification pc = e.getClassification();
		HourlyClassification hc = (HourlyClassification)pc;
		assertNotNull(hc);
		assertEquals(55.5, hc.getHourlyRate(), .01);
		
		PaymentSchedule ps = e.getSchedule();
		WeeklySchedule ws = (WeeklySchedule)ps;
		assertNotNull(ws);
		
		PaymentMethod pm = e.getMethod();
		HoldMethod hm = (HoldMethod)pm;
		assertNotNull(hm);
		
	}

}
