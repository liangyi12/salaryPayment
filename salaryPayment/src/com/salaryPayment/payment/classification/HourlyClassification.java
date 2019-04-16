package com.salaryPayment.payment.classification;

import java.util.HashMap;
import java.util.Map;

import com.salaryPayment.domain.TimeCard;

public class HourlyClassification extends PaymentClassification{
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
		timcards.put(tc.getDate(), tc);
	}

	public TimeCard getTimeCard(long date) {
		return timcards.get(date);
	}
	
	
	
}
