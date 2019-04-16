package com.salaryPayment.transaction;

import com.salaryPayment.database.PayrollDatabase;
import com.salaryPayment.domain.Employee;
import com.salaryPayment.domain.TimeCard;
import com.salaryPayment.payment.classification.HourlyClassification;
import com.salaryPayment.payment.classification.PaymentClassification;

public class TimeCardTransation implements Transaction {
	PayrollDatabase gpayrollDatabase = PayrollDatabase.gpayrollDatabase;
	
	private long date;
	private double hours;
	private int empid;
	
	public TimeCardTransation() {
		
	}
	
	public TimeCardTransation(long date, double hours, int empid) {
		this.date = date;
		this.hours = hours;
		this.empid = empid;
	}

	@Override
	public void excute() throws Exception {
		Employee e = gpayrollDatabase.getEmployee(empid);
		if(e != null) {
			PaymentClassification pc = e.getClassification();
			if (pc instanceof HourlyClassification) {
				HourlyClassification hc = (HourlyClassification)pc;
				TimeCard tc = new TimeCard(date, hours);
				hc.addTimeCard(tc);
			}else{
				throw new Exception("tried to add timeCard to non-hourly employee");
			}	
		}else{
			throw new Exception("No such employee");
		}
	}

}
