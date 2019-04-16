package com.salaryPayment.database;

import java.util.HashMap;
import java.util.Map;

import com.salaryPayment.domain.Employee;

public class PayrollDatabase {
	public static PayrollDatabase gpayrollDatabase = new PayrollDatabase();
	
	private Map<Integer, Employee> itsEmployees = new HashMap<Integer, Employee>();
	
	public PayrollDatabase() {
		
	}
	
	public void addEmployee(int empid, Employee e){
		itsEmployees.put(empid, e);
	}
	
	public Employee getEmployee(int empid) {
		return itsEmployees.get(empid);
	}
	
	public void clear() {
		itsEmployees.clear();
	}
	
	public void deteleEmployee(int empid) {
		itsEmployees.remove(empid);
	}

	public Employee getUnionMember(int itsMemberId) {
		return itsEmployees.get(itsMemberId);
	}

	public void addUnionMember(int memberid, Employee e) {
		itsEmployees.put(memberid, e);
	}
	

}
