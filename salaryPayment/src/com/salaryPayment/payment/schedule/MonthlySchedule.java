package com.salaryPayment.payment.schedule;

import java.util.Date;

import com.salaryPayment.util.DateUtil;


public class MonthlySchedule implements PaymentSchedule {

	@Override
	public boolean isPayDate(Date payDate) {
		return DateUtil.isLastDayOfMonth(payDate);
	}
}
