package com.salaryPayment.transaction.change;

import com.salaryPayment.domain.Employee;

public class ChangeNameTransation extends ChangeEmployeeTransation {
	private String name;
	
	public ChangeNameTransation() {
		
	}
	
	public ChangeNameTransation(int empid, String name) {
		super(empid);
		this.name = name;
	}

	@Override
	public void change(Employee e) {
		e.setName(name);

	}

}
