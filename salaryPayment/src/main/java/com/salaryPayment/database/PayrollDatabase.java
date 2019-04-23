package com.salaryPayment.database;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.salaryPayment.domain.Employee;

public class PayrollDatabase {
	public static PayrollDatabase gpayrollDatabase = new PayrollDatabase();
	
	private Map<Integer, Employee> itsEmployees = new HashMap<Integer, Employee>();
	
	private Map<Integer, Employee> itsMembers = new HashMap<Integer, Employee>();
	
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
		return itsMembers.get(itsMemberId);
	}

	public void addUnionMember(int memberid, Employee e) {
		itsMembers.put(memberid, e);
	}
	
	public void removeUnionMember(int memberid) {
		itsMembers.remove(memberid);
	}

	public List<Employee> getEmployees() {
		List<Employee> employees = new ArrayList<Employee>();
		for (Entry<Integer, Employee>  entry : itsEmployees.entrySet()) {
			employees.add((Employee)entry.getValue());
		}
		return employees;
	}
	

}
