package com.salaryPayment.payment.classification;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.salaryPayment.domain.Paycheck;
import com.salaryPayment.domain.TimeCard;

public class HourlyClassification implements PaymentClassification{
	private double hourlyRate;
	private Map<Long, TimeCard> timcards = new HashMap<Long, TimeCard>();
	
	
	public HourlyClassification() {
		
	}
	
	public HourlyClassification(double hourlyRate) {
		this.hourlyRate = hourlyRate;
	}

	public double getHourlyRate() {
		return hourlyRate;
	}

	public void setHourlyRate(double hourlyRate) {
		this.hourlyRate = hourlyRate;
	}

	public void addTimeCard(TimeCard tc) {
		timcards.put(tc.getDate().getTime(), tc);
	}

	public TimeCard getTimeCard(long date) {
		return timcards.get(date);
	}

	@Override
	public double calculatePay(Paycheck pc) {
		double grossPay = 0;
		Date payDate = pc.getPayDate();

		for (Entry<Long, TimeCard> entry : timcards.entrySet()) {
			TimeCard tc = entry.getValue();
			if (isInPayPeriod(tc, payDate)) {
				grossPay += calculatePayForTimeCard(tc);
			}
		}
		return grossPay;
	}
	
	private double calculatePayForTimeCard(TimeCard tc) {
		double hours = tc.getHours();
		double overtime = Math.max(0.0, hours - 8.0);
		double straightTime = hours - overtime;
		return straightTime * hourlyRate + overtime * hourlyRate * 1.5;
	}

	public boolean isInPayPeriod(TimeCard tc, Date payPeriod) {
		Date tc_date = tc.getDate();
		Date payPeriodEndDate = payPeriod;
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(payPeriod);
		calendar.add(Calendar.DAY_OF_MONTH, -4);
		
		Date payPeriodBeginDate = calendar.getTime();
		
		int lesser = payPeriodBeginDate.compareTo(tc_date); //<=0
		int greater = payPeriodEndDate.compareTo(tc_date); // >=0
		
		return (lesser <=0 && greater >= 0);
	}
	
	
	
}
