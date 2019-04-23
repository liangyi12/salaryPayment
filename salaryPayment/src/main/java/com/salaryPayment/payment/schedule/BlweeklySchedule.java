package com.salaryPayment.payment.schedule;

import java.util.Calendar;
import java.util.Date;

import com.salaryPayment.util.DateUtil;

public class BlweeklySchedule implements PaymentSchedule {

	@Override
	public boolean isPayDate(Date payDate) {	
		return DateUtil.isBlFridayOfMonth(payDate);
	}

	@Override
	public Date getPayPeriodStartDate(Date payDate) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(payDate);
		calendar.add(Calendar.DAY_OF_MONTH, -13);
		
		Date payPeriodBeginDate = calendar.getTime();
		
		return payPeriodBeginDate;
	}

}
