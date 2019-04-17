package com.salaryPayment.payment.schedule;

import java.util.Date;

import com.salaryPayment.util.DateUtil;

public class WeeklySchedule implements PaymentSchedule {

	@Override
	public boolean isPayDate(Date payDate) {
		return DateUtil.isFridayOfWeek(payDate);
	}

}
