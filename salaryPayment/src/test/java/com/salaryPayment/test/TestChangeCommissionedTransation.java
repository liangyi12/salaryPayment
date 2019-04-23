package com.salaryPayment.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.salaryPayment.database.PayrollDatabase;
import com.salaryPayment.domain.Employee;
import com.salaryPayment.payment.classification.CommissionedClassification;
import com.salaryPayment.payment.classification.PaymentClassification;
import com.salaryPayment.payment.schedule.BlweeklySchedule;
import com.salaryPayment.payment.schedule.PaymentSchedule;
import com.salaryPayment.transaction.add.AddHourlyEmployee;
import com.salaryPayment.transaction.change.ChangeCommissionedTransation;

public class TestChangeCommissionedTransation {
	PayrollDatabase gpayrollDatabase = PayrollDatabase.gpayrollDatabase;

	@Test
	public void payRollTest() throws Exception {
		int empid = 2;
		AddHourlyEmployee t = new AddHourlyEmployee(empid, "test", "home", 55.55);
		t.excute();
		
		Employee e = gpayrollDatabase.getEmployee(empid);
		assertNotNull(e);
		
		ChangeCommissionedTransation ct = new ChangeCommissionedTransation(empid, 1000.0, 44.4);
		ct.excute();
		
		Employee e1 = gpayrollDatabase.getEmployee(empid);
		assertNotNull(e1);
		
		PaymentClassification pc = e1.getClassification();
		CommissionedClassification cc = (CommissionedClassification)pc;
		assertNotNull(cc);
		assertEquals(1000.0, cc.getSalary(), .01);
		assertEquals(44.4, cc.getCommissionRate(), .01);
		
		PaymentSchedule ps = e1.getSchedule();
		BlweeklySchedule bs = (BlweeklySchedule)ps;
		assertNotNull(bs);
		
	}

}
