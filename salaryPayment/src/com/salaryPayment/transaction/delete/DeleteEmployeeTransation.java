package com.salaryPayment.transaction.delete;

import com.salaryPayment.database.PayrollDatabase;
import com.salaryPayment.transaction.Transaction;

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
