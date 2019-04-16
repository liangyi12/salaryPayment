package com.salaryPayment.transaction;

import com.salaryPayment.database.PayrollDatabase;

public class DeleteEmployeeTransation implements Transaction {
	private int empid = 1;
	PayrollDatabase gpayrollDatabase = PayrollDatabase.gpayrollDatabase;
	
	public DeleteEmployeeTransation() {
		
	}
	
	public DeleteEmployeeTransation(int empid) {
		this.empid = empid;
	}

	@Override
	public void excute() {
		gpayrollDatabase.deteleEmployee(empid);
	}

}
