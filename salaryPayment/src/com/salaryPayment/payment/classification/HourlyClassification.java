package com.salaryPayment.payment.classification;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
		List<Date> workDates = new ArrayList<Date>();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(pc.getPayDate());
		Date date = new Date();
		
		for (int i=1; i < 8; i++) {
			calendar.add(Calendar.DAY_OF_MONTH, -1);
			date = calendar.getTime();
			workDates.add(date);
		}
		
		for(Date date1 : workDates) {
			TimeCard tc = getTimeCard(date1.getTime());
			if (tc != null) {
				if (tc.getHours() > 8.0) {
					grossPay +=(8.0 +  (tc.getHours() - 8.0) * 1.5) * hourlyRate;
				}else{
					grossPay += tc.getHours() * hourlyRate;
				}
				
			}
		}
		return grossPay;
	}
	
	
	
}
