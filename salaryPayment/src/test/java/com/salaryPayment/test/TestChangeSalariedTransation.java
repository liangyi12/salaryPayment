package com.salaryPayment.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.salaryPayment.database.PayrollDatabase;
import com.salaryPayment.domain.Employee;
import com.salaryPayment.payment.classification.PaymentClassification;
import com.salaryPayment.payment.classification.SalariedClassification;
import com.salaryPayment.payment.schedule.MonthlySchedule;
import com.salaryPayment.payment.schedule.PaymentSchedule;
import com.salaryPayment.transaction.add.AddCommissionedEmployee;
import com.salaryPayment.transaction.change.ChangeClassificationTransation;
import com.salaryPayment.transaction.change.ChangeSalariedTransation;

public class TestChangeSalariedTransation {
	
	PayrollDatabase gpayrollDatabase = PayrollDatabase.gpayrollDatabase;

	@Test
	public void payRollTest() throws Exception {
		int empid = 2;
		AddCommissionedEmployee t = new AddCommissionedEmployee(empid, "bob", "home", 1000.0, 55.5);
		t.excute();
		
		Employee e = gpayrollDatabase.getEmployee(empid);
		assertNotNull(e);
		
		ChangeSalariedTransation cst = new ChangeSalariedTransation(empid, 10000.0);
		cst.excute();
		
		Employee e1 = gpayrollDatabase.getEmployee(empid);
		assertNotNull(e1);
		
		PaymentClassification pc = e1.getClassification();
		SalariedClassification sc = (SalariedClassification)pc;
		assertNotNull(sc);
		assertEquals(10000.0, sc.getSalary(), .01);
		
		PaymentSchedule ps = e1.getSchedule();
		MonthlySchedule ms = (MonthlySchedule)ps;
		assertNotNull(ms);
	}

}
