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
import com.salaryPayment.transaction.add.AddHourlyEmployee;
import com.salaryPayment.transaction.add.AddTimeCardTransation;
import com.salaryPayment.transaction.pay.PayDayTransation;

public class TestPaySingleHourlyEmployee {
	PayrollDatabase gpayrollDatabase = PayrollDatabase.gpayrollDatabase;
	int empid = 2;
	Calendar calendar = Calendar.getInstance();
	
	@Before
	public void setup() {
		AddHourlyEmployee t = new AddHourlyEmployee(empid, "test", "home", 15.25);
		t.excute();
		
		Employee e = gpayrollDatabase.getEmployee(empid);
		e.setAffiliation(new NoAffiliation());
	}
	
	
	@Test
	public void testPaySingleHourlyEmployeeOnWrongDate() throws Exception {
		
		calendar.set(2019, Calendar.APRIL, 4);
		Date payDate = calendar.getTime();
		
		PayDayTransation pt = new PayDayTransation(payDate);
		pt.excute();
		
		Paycheck pc = pt.getPayCheck(empid);
		assertNull(pc);
	}

	@Test
	public void testPaySingleHourlyEmployeeNoTimeCards() throws Exception {
		
		calendar.set(2019, Calendar.APRIL, 5);
		Date payDate = calendar.getTime();
		
		PayDayTransation pt = new PayDayTransation(payDate);
		pt.excute();
		
		validatePayCheck(pt, payDate, 0.00, 0.00, 0.00);
		
	}
	
	@Test
	public void testPaySingleHourlyEmployeeOneTimeCard() throws Exception {				
		calendar.set(2019, Calendar.APRIL, 5);
		Date payDate = calendar.getTime();
		AddTimeCardTransation at = new AddTimeCardTransation(payDate, 7.0, empid);
		at.excute();
		
		PayDayTransation pt = new PayDayTransation(payDate);
		pt.excute();
		
		validatePayCheck(pt, payDate, 7.0 * 15.25, 0.00, 7.0 * 15.25);
	}
	
	@Test
	public void testPaySingleHourlyEmployeeOneTimeCardOverEightHour() throws Exception {		
		calendar.set(2019, Calendar.APRIL, 4);
		Date timeCardDate = calendar.getTime();
		
		calendar.set(2019, Calendar.APRIL, 5);
		Date payDate = calendar.getTime();
		AddTimeCardTransation at = new AddTimeCardTransation(timeCardDate, 9.0, empid);
		at.excute();
		
		PayDayTransation pt = new PayDayTransation(payDate);
		pt.excute();
		
		validatePayCheck(pt, payDate, (8.0 + 1.5) * 15.25, 0.00, (8.0 + 1.5)  * 15.25);
	}
	
	@Test
	public void testPaySingleHourlyEmployeeMoreThanOneTimeCard() throws Exception {
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
		
		validatePayCheck(pt, payDate, 2 * (8.0 + 1.5) * 15.25, 0.00, 2 * (8.0 + 1.5)  * 15.25);
		
	}
	
	@Test
	public void testPaySingleHourlyEmployeeWithNoUsedTimeCard() throws Exception {
		calendar.set(2019, Calendar.APRIL, 4);
		Date timeCardDate = calendar.getTime();
		
		calendar.set(2019, Calendar.APRIL, 5);
		Date payDate = calendar.getTime();
		AddTimeCardTransation at = new AddTimeCardTransation(timeCardDate, 9.0, empid);
		at.excute();
		
		calendar.set(2019, Calendar.APRIL, 6);
		timeCardDate = calendar.getTime();
		at = new AddTimeCardTransation(timeCardDate, 8.0, empid);
		at.excute();
		
		PayDayTransation pt = new PayDayTransation(payDate);
		pt.excute();
		
		validatePayCheck(pt, payDate, (8.0 + 1.5) * 15.25, 0.00, (8.0 + 1.5)  * 15.25);
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
