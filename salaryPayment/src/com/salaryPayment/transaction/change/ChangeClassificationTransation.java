package com.salaryPayment.transaction.change;

import com.salaryPayment.domain.Employee;
import com.salaryPayment.payment.classification.PaymentClassification;
import com.salaryPayment.payment.schedule.PaymentSchedule;

public abstract class ChangeClassificationTransation extends ChangeEmployeeTransation{
	
	public ChangeClassificationTransation() {
		
	}
	
	public ChangeClassificationTransation(int empid) {
		super(empid);
	}

	@Override
	public void change(Employee e) {
		PaymentClassification pc = getClassification();
		e.setClassification(pc);
		
		PaymentSchedule ps = getSchedule();
		e.setSchedule(ps);
	}
	
	public abstract PaymentClassification getClassification();
	
	public abstract PaymentSchedule getSchedule();

}
