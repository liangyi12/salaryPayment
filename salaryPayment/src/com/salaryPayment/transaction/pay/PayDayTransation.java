package com.salaryPayment.transaction.pay;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.salaryPayment.database.PayrollDatabase;
import com.salaryPayment.domain.Employee;
import com.salaryPayment.domain.Paycheck;
import com.salaryPayment.transaction.Transaction;

public class PayDayTransation implements Transaction {
	private Date payDate;
	Map<Integer, Paycheck> paychecks = new HashMap<Integer, Paycheck>();
	
	PayrollDatabase gpayrollDatabase = PayrollDatabase.gpayrollDatabase;
	
	public PayDayTransation() {
		
	}
	
	public PayDayTransation(Date payDate) {
		this.payDate = payDate;
	}

	@Override
	public void excute() throws Exception {
		List<Employee> employees = gpayrollDatabase.getEmployees();
		for (Employee e : employees) {
			boolean isPayDay = e.isPayDate(payDate);
			if (isPayDay) {
				Paycheck pc = new Paycheck(e.getPayPeriodStartDate(payDate), payDate);
				paychecks.put(e.getEmpid(), pc);
				e.payDay(pc);
			}
		}

	}

	public Paycheck getPayCheck(int empid) {
		return paychecks.get(empid);
	}

}
