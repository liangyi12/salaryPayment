package com.salaryPayment.test;

import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.Date;

import org.junit.Test;

import com.salaryPayment.affiliation.NoAffiliation;
import com.salaryPayment.database.PayrollDatabase;
import com.salaryPayment.domain.Employee;
import com.salaryPayment.domain.Paycheck;
import com.salaryPayment.transaction.add.AddHourlyEmployee;
import com.salaryPayment.transaction.add.AddTimeCardTransation;
import com.salaryPayment.transaction.pay.PayDayTransation;

public class TestPaySingleHourlyEmployee {
	PayrollDatabase gpayrollDatabase = PayrollDatabase.gpayrollDatabase;
	
	@Test
	public void testPaySingleHourlyEmployeeOnWrongDate() throws Exception {
		int empid = 2;
		AddHourlyEmployee t = new AddHourlyEmployee(empid, "test", "home", 15.25);
		t.excute();
		
		Employee e = gpayrollDatabase.getEmployee(empid);
		e.setAffiliation(new NoAffiliation());
		
		Calendar calendar = Calendar.getInstance();
		calendar.set(2019, Calendar.APRIL, 4);
		Date payDate = calendar.getTime();
		
		PayDayTransation pt = new PayDayTransation(payDate);
		pt.excute();
		
		Paycheck pc = pt.getPayCheck(empid);
		assertNull(pc);
	}

	@Test
	public void testPaySingleHourlyEmployeeNoTimeCards() throws Exception {
		int empid = 2;
		AddHourlyEmployee t = new AddHourlyEmployee(empid, "test", "home", 15.25);
		t.excute();
		
		Employee e = gpayrollDatabase.getEmployee(empid);
		e.setAffiliation(new NoAffiliation());
		
		Calendar calendar = Calendar.getInstance();
		calendar.set(2019, Calendar.APRIL, 5);
		Date payDate = calendar.getTime();
		
		PayDayTransation pt = new PayDayTransation(payDate);
		pt.excute();
		
		Paycheck pc = pt.getPayCheck(empid);
		assertNotNull(pc);
		assertEquals(payDate, pc.getPayDate());
		assertEquals(0.00, pc.getGrossPay(), .001);
		assertEquals(0.00, pc.getDeductions(), .001);
		assertEquals(0.00, pc.getNetPay(), .001);
	}
	
	@Test
	public void testPaySingleHourlyEmployeeOneTimeCard() throws Exception {
		int empid = 2;
		AddHourlyEmployee t = new AddHourlyEmployee(empid, "test", "home", 15.25);
		t.excute();
		
		Employee e = gpayrollDatabase.getEmployee(empid);
		e.setAffiliation(new NoAffiliation());
		
		Calendar calendar = Calendar.getInstance();
		calendar.set(2019, Calendar.APRIL, 4);
		Date timeCardDate = calendar.getTime();
		
		calendar.set(2019, Calendar.APRIL, 5);
		Date payDate = calendar.getTime();
		AddTimeCardTransation at = new AddTimeCardTransation(timeCardDate, 7.0, empid);
		at.excute();
		
		PayDayTransation pt = new PayDayTransation(payDate);
		pt.excute();
		
		Paycheck pc = pt.getPayCheck(empid);
		assertNotNull(pc);
		assertEquals(payDate, pc.getPayDate());
		assertEquals(7.0 * 15.25, pc.getGrossPay(), .001);
		assertEquals(0.00, pc.getDeductions(), .001);
		assertEquals(7.0 * 15.25, pc.getNetPay(), .001);
	}
	
	@Test
	public void testPaySingleHourlyEmployeeOneTimeCard2() throws Exception {
		int empid = 2;
		AddHourlyEmployee t = new AddHourlyEmployee(empid, "test", "home", 15.25);
		t.excute();
		
		Employee e = gpayrollDatabase.getEmployee(empid);
		e.setAffiliation(new NoAffiliation());
		
		Calendar calendar = Calendar.getInstance();
		calendar.set(2019, Calendar.APRIL, 4);
		Date timeCardDate = calendar.getTime();
		
		calendar.set(2019, Calendar.APRIL, 5);
		Date payDate = calendar.getTime();
		AddTimeCardTransation at = new AddTimeCardTransation(timeCardDate, 9.0, empid);
		at.excute();
		
		PayDayTransation pt = new PayDayTransation(payDate);
		pt.excute();
		
		Paycheck pc = pt.getPayCheck(empid);
		assertNotNull(pc);
		assertEquals(payDate, pc.getPayDate());
		assertEquals((8.0 + 1.5) * 15.25, pc.getGrossPay(), .001);
		assertEquals(0.00, pc.getDeductions(), .001);
		assertEquals((8.0 + 1.5)  * 15.25, pc.getNetPay(), .001);
	}
	
	@Test
	public void testPaySingleHourlyEmployeeMoreThanOneTimeCard() throws Exception {
		int empid = 2;
		AddHourlyEmployee t = new AddHourlyEmployee(empid, "test", "home", 15.25);
		t.excute();
		
		Employee e = gpayrollDatabase.getEmployee(empid);
		e.setAffiliation(new NoAffiliation());
		
		Calendar calendar = Calendar.getInstance();
		calendar.set(2019, Calendar.APRIL, 4);
		Date timeCardDate = calendar.getTime();
		
		calendar.set(2019, Calendar.APRIL, 5);
		Date payDate = calendar.getTime();
		AddTimeCardTransation at = new AddTimeCardTransation(timeCardDate, 9.0, empid);
		at.excute();
		
		calendar.set(2019, Calendar.APRIL, 3);
		timeCardDate = calendar.getTime();
		at = new AddTimeCardTransation(timeCardDate, 9.0, empid);
		at.excute();
		
		PayDayTransation pt = new PayDayTransation(payDate);
		pt.excute();
		
		Paycheck pc = pt.getPayCheck(empid);
		assertNotNull(pc);
		assertEquals(payDate, pc.getPayDate());
		assertEquals(2 * (8.0 + 1.5) * 15.25, pc.getGrossPay(), .001);
		assertEquals(0.00, pc.getDeductions(), .001);
		assertEquals(2 * (8.0 + 1.5)  * 15.25, pc.getNetPay(), .001);
	}

}
