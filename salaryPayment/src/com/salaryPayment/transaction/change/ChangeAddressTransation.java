package com.salaryPayment.transaction.change;

import com.salaryPayment.domain.Employee;

public class ChangeAddressTransation extends ChangeEmployeeTransation {
	private String address;
	
	public ChangeAddressTransation() {
		
	}
	
	public ChangeAddressTransation(int empid, String address) {
		super(empid);
		this.address = address;
	}

	@Override
	public void change(Employee e) {
		e.setAddress(address);
	}

}
