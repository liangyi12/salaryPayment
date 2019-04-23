package com.salaryPayment.transaction.change;

import com.salaryPayment.domain.Employee;
import com.salaryPayment.payment.method.PaymentMethod;

public abstract class ChangeMethodTransation extends ChangeEmployeeTransation {
	
	public ChangeMethodTransation(){
		
	}
	
	public ChangeMethodTransation(int empid) {
		super(empid);
	}

	@Override
	public void change(Employee e) {
		PaymentMethod pm = getMethod();
		e.setMethod(pm);
	}
	
	public abstract PaymentMethod getMethod();

}
