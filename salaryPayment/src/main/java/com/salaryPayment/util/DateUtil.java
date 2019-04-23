package com.salaryPayment.util;

import java.util.Calendar;
import java.util.Date;

public class DateUtil {
	public static boolean isLastDayOfMonth(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) + 1);
		if (calendar.get(Calendar.DAY_OF_MONTH) == 1) {
			return true;
		}
		return false;
	}
	
	public static boolean isFridayOfWeek(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY) {
			return true;
		}
		return false;
	}
	
	public static boolean isBlFridayOfMonth(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		if (calendar.get(Calendar.WEEK_OF_YEAR) % 2 ==0 && calendar.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY) {
			return true;
		}
		return false;
	}

	public static int numberOfFridayBetweenTwoDate(Date payPeriodStartDate, Date payPeriodEndDate) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(payPeriodStartDate);
		
		int beginDay = calendar.get(Calendar.DAY_OF_MONTH);
		calendar.setTime(payPeriodEndDate);
		
		int endDay = calendar.get(Calendar.DAY_OF_MONTH);
		
		int numberOfFridayBetweenTwoDate = 0;
		
		for (int i = beginDay; i <= endDay; i++) {
			calendar.set(Calendar.DAY_OF_MONTH, i);
			if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY) {
				numberOfFridayBetweenTwoDate++;
			}
		}
		
		return numberOfFridayBetweenTwoDate;
	}

	public static boolean isBetween(Date date, Date startDate, Date endDate) {
		int lesser = startDate.compareTo(date); //<=0
		int greater = endDate.compareTo(date); // >=0
		
		return (lesser <=0 && greater >= 0);
	}

}
