package com.salaryPayment.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.salaryPayment.database.PayrollDatabase;
import com.salaryPayment.domain.Employee;
import com.salaryPayment.payment.classification.CommissionedClassification;
import com.salaryPayment.payment.classification.PaymentClassification;
import com.salaryPayment.payment.method.HoldMethod;
import com.salaryPayment.payment.method.PaymentMethod;
import com.salaryPayment.payment.schedule.BlweeklySchedule;
import com.salaryPayment.payment.schedule.PaymentSchedule;
import com.salaryPayment.transaction.AddCommissionedEmployee;

public class TestAddCommissionedEmployee {
	PayrollDatabase gpayrollDatabase = PayrollDatabase.gpayrollDatabase;


	@Test
	public void payrollTest() {
		int empid = 3;
		AddCommissionedEmployee t = new AddCommissionedEmployee(empid, "Bob", "home", 10000.0, 55.5);
		t.excute();
		
		Employee e = gpayrollDatabase.getEmployee(empid);
		assertNotNull(e);
		
		PaymentClassification pc = e.getClassification();
		CommissionedClassification cc = (CommissionedClassification)pc;
		assertNotNull(cc);
		assertEquals(10000.0, cc.getSalary(), .01);
		assertEquals(55.5, cc.getCommissionRate(), .01);
		
		PaymentSchedule ps = e.getSchedule();
		BlweeklySchedule bs = (BlweeklySchedule)ps;
		assertNotNull(bs);
		
		PaymentMethod pm = e.getMethod();
		HoldMethod hm = (HoldMethod)pm;
		assertNotNull(hm);
		
		
	}

}
