package com.salaryPayment.payment.schedule;

import java.util.Date;

public class BlweeklySchedule implements PaymentSchedule {

	@Override
	public boolean isPayDate(Date payDate) {
		
		return false;
	}

}
