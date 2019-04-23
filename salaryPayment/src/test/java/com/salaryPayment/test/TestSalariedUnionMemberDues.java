package com.salaryPayment.test;

import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.Date;

import org.junit.Test;

import com.salaryPayment.domain.Paycheck;
import com.salaryPayment.transaction.add.AddSalariedEmployee;
import com.salaryPayment.transaction.add.AddServiceChargeTransation;
import com.salaryPayment.transaction.change.ChangeMemberTransation;
import com.salaryPayment.transaction.pay.PayDayTransation;

public class TestSalariedUnionMemberDues {
	int empid = 1;

	@Test
	public void testSalariedUnionMemberDues() throws Exception {
		AddSalariedEmployee t = new AddSalariedEmployee(empid, "bob", "home", 1000.0);
		t.excute();
		
		int memberId = 7734;
		ChangeMemberTransation ct = new ChangeMemberTransation(empid, memberId, 22.0);
		ct.excute();
		
		Calendar calendar = Calendar.getInstance();
		calendar.set(2019, Calendar.MARCH, 31);
		Date payDate = calendar.getTime();
		
		PayDayTransation pt = new PayDayTransation(payDate);
		pt.excute();
		
		validatePayCheck(pt, payDate, 1000.0, 5 * 22.0, 1000.0 - 5*22.0);
		
	}
	
	@Test
	public void testSalariedUnionMemberOneServiCharge() throws Exception {
		AddSalariedEmployee t = new AddSalariedEmployee(empid, "bob", "home", 1000.0);
		t.excute();
		
		int memberId = 7734;
		ChangeMemberTransation ct = new ChangeMemberTransation(empid, memberId, 22.0);
		ct.excute();
		
		Calendar calendar = Calendar.getInstance();
		calendar.set(2019, Calendar.MARCH, 31);
		Date payDate = calendar.getTime();
		
		AddServiceChargeTransation act = new AddServiceChargeTransation(memberId, payDate, 11.4);
		act.excute();
		
		PayDayTransation pt = new PayDayTransation(payDate);
		pt.excute();
		
		validatePayCheck(pt, payDate, 1000.0, 5 * 22.0 + 11.4, 1000.0 - 5*22.0 - 11.4);
	}
	
	@Test
	public void testSalariedUnionMemberOneMoreServiCharge() throws Exception {
		AddSalariedEmployee t = new AddSalariedEmployee(empid, "bob", "home", 1000.0);
		t.excute();
		
		int memberId = 7734;
		ChangeMemberTransation ct = new ChangeMemberTransation(empid, memberId, 22.0);
		ct.excute();
		
		Calendar calendar = Calendar.getInstance();
		calendar.set(2019, Calendar.MARCH, 31);
		Date payDate = calendar.getTime();
		
		AddServiceChargeTransation act = new AddServiceChargeTransation(memberId, payDate, 11.4);
		act.excute();
		
		calendar.set(2019, Calendar.MARCH, 30);
		Date timeDate = calendar.getTime();
		
		act = new AddServiceChargeTransation(memberId, timeDate, 12.4);
		act.excute();
		
		PayDayTransation pt = new PayDayTransation(payDate);
		pt.excute();
		
		validatePayCheck(pt, payDate, 1000.0, 5 * 22.0 + 11.4 + 12.4, 1000.0 - 5*22.0 - 11.4 - 12.4);
	}
	
	@Test
	public void testSalariedUnionMemberWithNoUsedServiCharge() throws Exception {
		AddSalariedEmployee t = new AddSalariedEmployee(empid, "bob", "home", 1000.0);
		t.excute();
		
		int memberId = 7734;
		ChangeMemberTransation ct = new ChangeMemberTransation(empid, memberId, 22.0);
		ct.excute();
		
		Calendar calendar = Calendar.getInstance();
		calendar.set(2019, Calendar.MARCH, 31);
		Date payDate = calendar.getTime();
		
		AddServiceChargeTransation act = new AddServiceChargeTransation(memberId, payDate, 11.4);
		act.excute();
		
		calendar.set(2019, Calendar.FEBRUARY, 10);
		Date timeDate = calendar.getTime();
		
		act = new AddServiceChargeTransation(memberId, timeDate, 12.4);
		act.excute();
		
		PayDayTransation pt = new PayDayTransation(payDate);
		pt.excute();
		
		validatePayCheck(pt, payDate, 1000.0, 5 * 22.0 + 11.4, 1000.0 - 5*22.0 - 11.4);
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
