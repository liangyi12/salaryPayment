package com.salaryPayment.test;

import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import com.salaryPayment.affiliation.NoAffiliation;
import com.salaryPayment.database.PayrollDatabase;
import com.salaryPayment.domain.Employee;
import com.salaryPayment.domain.Paycheck;
import com.salaryPayment.transaction.add.AddCommissionedEmployee;
import com.salaryPayment.transaction.add.AddSalesReceiptTransation;
import com.salaryPayment.transaction.pay.PayDayTransation;

public class TestPayCommissionedEmployee {
	PayrollDatabase gpayrollDatabase = PayrollDatabase.gpayrollDatabase;
	int empid = 3;
	Calendar calendar = Calendar.getInstance();
	
	@Before
	public void setup() {
		AddCommissionedEmployee t = new AddCommissionedEmployee(empid, "bob", "home", 1000.0, 33.4);
		t.excute();
		
		Employee e = gpayrollDatabase.getEmployee(empid);
		e.setAffiliation(new NoAffiliation());
	}
	
	@Test
	public void TestPayCommissionedEmployeeWithWrongDate() throws Exception {
		calendar.set(2019, Calendar.JANUARY, 18);
		Date payDate = calendar.getTime();
		
		PayDayTransation pt = new PayDayTransation(payDate);
		pt.excute();	
		
		Paycheck pc = pt.getPayCheck(empid);
		assertNull(pc);
	}

	@Test
	public void TestPayCommissionedEmployeeWithNoSalesReceipt() throws Exception {
		calendar.set(2019, Calendar.JANUARY, 11);
		Date payDate = calendar.getTime();
		
		PayDayTransation pt = new PayDayTransation(payDate);
		pt.excute();	
		
		validatePayCheck(pt, payDate, 1000.00, 0.00, 1000.00);
	}
	
	@Test
	public void TestPayCommissionedEmployeeWithOneSalesReceipt() throws Exception {
		calendar.set(2019, Calendar.JANUARY, 11);
		Date payDate = calendar.getTime();
		
		AddSalesReceiptTransation at = new AddSalesReceiptTransation(payDate, 12, empid);
		at.excute();
		
		PayDayTransation pt = new PayDayTransation(payDate);
		pt.excute();	
		
		validatePayCheck(pt, payDate, 1000.00 + 12 * 33.4, 0.00, 1000.00 + 12 * 33.4);
	}
	
	@Test
	public void TestPayCommissionedEmployeeWithOneMoreSalesReceipt() throws Exception {
		calendar.set(2019, Calendar.JANUARY, 11);
		Date payDate = calendar.getTime();
		
		calendar.set(2019, Calendar.JANUARY, 10);
		Date timeDate = calendar.getTime();
		
		AddSalesReceiptTransation at = new AddSalesReceiptTransation(timeDate, 11, empid);
		at.excute();
		
		at = new AddSalesReceiptTransation(payDate, 12, empid);
		at.excute();
		
		PayDayTransation pt = new PayDayTransation(payDate);
		pt.excute();	
		
		validatePayCheck(pt, payDate, 1000.00 + 23 * 33.4, 0.00, 1000.00 + 23 * 33.4);
	}
	
	@Test
	public void TestPayCommissionedEmployeeWithNoUsedSalesReceipt() throws Exception {
		calendar.set(2019, Calendar.JANUARY, 11);
		Date payDate = calendar.getTime();
		
		calendar.set(2019, Calendar.JANUARY, 12);
		Date timeDate = calendar.getTime();
		
		AddSalesReceiptTransation at = new AddSalesReceiptTransation(timeDate, 11, empid);
		at.excute();
		
		at = new AddSalesReceiptTransation(payDate, 12, empid);
		at.excute();
		
		PayDayTransation pt = new PayDayTransation(payDate);
		pt.excute();	
		
		validatePayCheck(pt, payDate, 1000.00 + 12 * 33.4, 0.00, 1000.00 + 12 * 33.4);
	}
	
	public void validatePayCheck(PayDayTransation pt, Date payDate, double grossPay, double deductions, double netpay) {
		Paycheck pc = pt.getPayCheck(empid);
		assertNotNull(pc);
		assertEquals(payDate, pc.getPayPeriodEndDate());
		assertEquals(grossPay, pc.getGrossPay(), .001);
		assertEquals(deductions, pc.getDeductions(), .001);
		assertEquals(netpay, pc.getNetPay(), .001);
	}

}
