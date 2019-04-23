package com.salaryPayment.transaction.add;

import com.salaryPayment.database.PayrollDatabase;
import com.salaryPayment.domain.Employee;
import com.salaryPayment.payment.classification.PaymentClassification;
import com.salaryPayment.payment.method.HoldMethod;
import com.salaryPayment.payment.method.PaymentMethod;
import com.salaryPayment.payment.schedule.PaymentSchedule;
import com.salaryPayment.transaction.Transaction;

public abstract class AddEmployeeTransaction implements Transaction{
	private int itsEmpid;
	private String itsName;
	private String itsAddress;
	
	PayrollDatabase gpayrollDatabase = PayrollDatabase.gpayrollDatabase;
	
	public AddEmployeeTransaction() {
		
	}
	
	public AddEmployeeTransaction(int empid, String name, String address) {
		itsEmpid = empid;
		itsName = name;
		itsAddress = address;
	}
	
	abstract PaymentClassification getClassification();
	
	abstract PaymentSchedule getSchedule();

	@Override
	public void excute() {
		PaymentClassification pc = getClassification();
		PaymentSchedule ps = getSchedule();
		PaymentMethod pm = new HoldMethod();
		Employee e = new Employee(itsEmpid, itsName, itsAddress);
		e.setClassification(pc);
		e.setSchedule(ps);
		e.setMethod(pm);
		gpayrollDatabase.addEmployee(itsEmpid, e);
		
	}

}
