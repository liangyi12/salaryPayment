package com.salaryPayment.payment.schedule;

import java.util.Calendar;
import java.util.Date;

import com.salaryPayment.util.DateUtil;


public class MonthlySchedule implements PaymentSchedule {

	@Override
	public boolean isPayDate(Date payDate) {
		return DateUtil.isLastDayOfMonth(payDate);
	}

	@Override
	public Date getPayPeriodStartDate(Date payDate) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(payDate);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		
		Date payPeriodBeginDate = calendar.getTime();
		
		return payPeriodBeginDate;
	}
}
