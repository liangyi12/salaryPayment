package com.salaryPayment.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.salaryPayment.database.PayrollDatabase;
import com.salaryPayment.domain.Employee;
import com.salaryPayment.domain.SalesReceipt;
import com.salaryPayment.payment.classification.CommissionedClassification;
import com.salaryPayment.payment.classification.PaymentClassification;
import com.salaryPayment.transaction.add.AddCommissionedEmployee;
import com.salaryPayment.transaction.add.AddSalesReceiptTransation;

public class TestAddSalesReceiptTransation {
	PayrollDatabase gpayrollDatabase = PayrollDatabase.gpayrollDatabase;

	@Test
	public void payRolltest() throws Exception {
		int empid = 2;
		AddCommissionedEmployee t = new AddCommissionedEmployee(empid, "test", "hoe", 1000.0, 55.5);
		t.excute();
		
		AddSalesReceiptTransation st = new AddSalesReceiptTransation(12333, 15, empid);
		st.excute();
		
		Employee e = gpayrollDatabase.getEmployee(empid);
		assertNotNull(e);
		
		PaymentClassification pc = e.getClassification();
		CommissionedClassification cc = (CommissionedClassification)pc;
		assertNotNull(cc);
		
		SalesReceipt sr = cc.getSalesReceipt(12333);
		assertNotNull(sr);
		assertEquals(15, sr.getAmount());
	}

}
