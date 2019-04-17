package com.salaryPayment.transaction.change;

import com.salaryPayment.database.PayrollDatabase;
import com.salaryPayment.domain.Employee;
import com.salaryPayment.transaction.Transaction;

public abstract class ChangeEmployeeTransation implements Transaction{
	private int empid;
	
	PayrollDatabase gpayrollDatabase = PayrollDatabase.gpayrollDatabase;
	
	public ChangeEmployeeTransation() {
		
	}
	
	public ChangeEmployeeTransation(int empid) {
		this.empid = empid;
	}

	@Override
	public void excute() throws Exception {
		Employee e = gpayrollDatabase.getEmployee(empid);
		if (e != null) {
			change(e);
		}else{
			throw new Exception("no such employee with empid " + empid);
		}
	}
	
	public abstract void change(Employee e);

}
