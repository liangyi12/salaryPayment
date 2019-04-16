package com.salaryPayment.test.add;

import static org.junit.Assert.*;

import org.junit.Test;

import com.salaryPayment.database.PayrollDatabase;
import com.salaryPayment.domain.Employee;
import com.salaryPayment.payment.classification.PaymentClassification;
import com.salaryPayment.payment.classification.SalariedClassification;
import com.salaryPayment.payment.method.HoldMethod;
import com.salaryPayment.payment.method.PaymentMethod;
import com.salaryPayment.payment.schedule.MonthlySchedule;
import com.salaryPayment.payment.schedule.PaymentSchedule;
import com.salaryPayment.transaction.add.AddSalariedEmployee;

public class TestAddSalariedEmployee {
	PayrollDatabase gpayrollDatabase = PayrollDatabase.gpayrollDatabase;

	@Test
	public void payrollTest() {
		int empId = 1;
		AddSalariedEmployee t = new AddSalariedEmployee(empId, "bob", "home", 1000.00);
		t.excute();
		
		Employee e = gpayrollDatabase.getEmployee(empId);
		assertEquals("bob", e.getItsName());
		
		PaymentClassification pc = e.getClassification();
		SalariedClassification sc = (SalariedClassification)pc;
		assertNotNull(sc);
		assertEquals(1000.00, sc.getSalary(), .001);
		
		PaymentSchedule ps = e.getSchedule();
		MonthlySchedule ms = (MonthlySchedule)ps;
		assertNotNull(ms);
		
		PaymentMethod pm = e.getMethod();
		HoldMethod hm = (HoldMethod)pm;
		assertNotNull(hm);
		
		
	}

}
