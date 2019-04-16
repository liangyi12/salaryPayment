package com.salaryPayment.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.salaryPayment.database.PayrollDatabase;
import com.salaryPayment.domain.Employee;
import com.salaryPayment.transaction.AddHourlyEmployee;
import com.salaryPayment.transaction.DeleteEmployeeTransation;

public class TestDeleteEmployee {
	PayrollDatabase gpayrollDatabase = PayrollDatabase.gpayrollDatabase;

	@Test
	public void testDeleteEmployee() {
		int empid = 1;
		AddHourlyEmployee t = new AddHourlyEmployee(empid, "test", "home", 55.5);
		t.excute();
		
		Employee e = gpayrollDatabase.getEmployee(empid);
		assertNotNull(e);
		
		DeleteEmployeeTransation dt = new DeleteEmployeeTransation(empid);
		dt.excute();
		
		Employee e1 = gpayrollDatabase.getEmployee(empid);
		assertNull(e1);
		
		
	}

}
