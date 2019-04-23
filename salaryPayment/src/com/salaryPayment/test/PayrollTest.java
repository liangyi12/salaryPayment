package com.salaryPayment.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.salaryPayment.payroll.Payroll;


public class PayrollTest {

	
	Payroll payroll = null;
	@Before
	public void setUp() throws Exception {
		payroll = new Payroll();
	}

	@After
	public void tearDown() throws Exception {
		payroll = null;
	}

	@Test
	public void testPayMonthlyEmployee() throws Exception{
		String[][] commands = new String[][] { 
			{ "AddMonthlyEmployee", "101", "Bob", "Beijing", "1000.0" },
			{ "AddMonthlyEmployee", "102", "Joe", "Beijing", "2000.0" }
		};
		payroll.parse(commands);
		
		
		Date payDay = parseDate("2019-4-30");
		Map<String, Double> payments = payroll.payFor(payDay);
		Assert.assertEquals(2, payments.size());
		Assert.assertEquals(Double.valueOf(1000.0), payments.get("101"),0.001);
		Assert.assertEquals(Double.valueOf(2000.0), payments.get("102"),0.001);
		
	}
	
	@Test
	public void testPayHourlyEmployee() throws Exception{
		String[][] commands = new String[][] { 			
			{ "AddHourlyEmployee", "102", "Joe", "Beijing", "15.0" },
			{ "AddTimeCard", "102", "2019-4-24", "8" } 
		};
		payroll.parse(commands);
		
		Date payDay = parseDate("2019-4-26"); //Friday
		Map<String, Double> payments = payroll.payFor(payDay);

		Assert.assertEquals(1, payments.size());
		Assert.assertEquals(15.0 * 8, (payments.get("102")).doubleValue(), 0.01);
		
	}
	
	@Test
	public void testChangeEmployeeType() throws Exception {

		String[][] commands = new String[][] { 
			{ "AddMonthlyEmployee", "101", "Bob", "Beijing", "1000" }};
		
		
		payroll.parse(commands);

		Date payDay = parseDate("2019-4-30");
		Map<String, Double> payments = payroll.payFor(payDay);

		
		Assert.assertEquals(1, payments.size());
		Assert.assertEquals(Double.valueOf(1000), payments.get("101"),0.01);

		commands = new String[][] { { "ChangeToHourlyEmployee", "101", "25.0" } };

		payroll.parse(commands);
		
		payments = payroll.payFor(payDay);
		Assert.assertEquals(0, payments.size());

	}

	public Date parseDate(String s) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.parse(s);
	}

}
