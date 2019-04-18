package com.salaryPayment.test;

import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.Date;

import org.junit.Test;

import com.salaryPayment.affiliation.NoAffiliation;
import com.salaryPayment.database.PayrollDatabase;
import com.salaryPayment.domain.Employee;
import com.salaryPayment.domain.Paycheck;
import com.salaryPayment.transaction.add.AddSalariedEmployee;
import com.salaryPayment.transaction.pay.PayDayTransation;

public class TestPaySingleSalariedEmployee {
	PayrollDatabase gpayrollDatabase = PayrollDatabase.gpayrollDatabase;

	@Test
	public void testPaySingleSalariedEmployee() throws Exception {
		int empid = 1;
		AddSalariedEmployee t = new AddSalariedEmployee(empid, "Bob", "home", 1000.00);
		t.excute();
		
		Employee e = gpayrollDatabase.getEmployee(empid);
		e.setAffiliation(new NoAffiliation());
		
		Calendar calendar = Calendar.getInstance();
		calendar.set(2019, Calendar.MARCH, 31);
		Date payDate = calendar.getTime();
		
		PayDayTransation pt = new PayDayTransation(payDate); //得到所有当天发薪水的employee
		pt.excute();
		
		Paycheck pc = pt.getPayCheck(empid);
		assertNotNull(pc);
		assertEquals(payDate, pc.getPayPeriodEndDate());
		assertEquals(1000.00, pc.getGrossPay(), .001);
		assertEquals(0.00, pc.getDeductions(), .001);
		assertEquals(1000.00, pc.getNetPay(), .001);
		
	}
	
	@Test
	public void testPaySingleSalariedEmployeeOnWrondDay() throws Exception {
		int empid = 1;
		AddSalariedEmployee t = new AddSalariedEmployee(empid, "Bob", "home", 1000.00);
		t.excute();
		
		Calendar calendar = Calendar.getInstance();
		calendar.set(2019, Calendar.MARCH, 11);
		Date payDate = calendar.getTime();
		
		PayDayTransation pt = new PayDayTransation(payDate); //得到所有当天发薪水的employee
		pt.excute();
		
		Paycheck pc = pt.getPayCheck(empid);
		assertNull(pc);
	}

}
