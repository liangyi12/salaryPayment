package com.salaryPayment.payment.schedule;

import java.util.Date;

public interface PaymentSchedule {

	public boolean isPayDate(Date payDate);

	public Date getPayPeriodStartDate(Date payDate);

}
