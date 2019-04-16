package com.salaryPayment.transaction.add;

import com.salaryPayment.database.PayrollDatabase;
import com.salaryPayment.domain.Employee;
import com.salaryPayment.domain.SalesReceipt;
import com.salaryPayment.payment.classification.CommissionedClassification;
import com.salaryPayment.payment.classification.PaymentClassification;
import com.salaryPayment.transaction.Transaction;

public class AddSalesReceiptTransation implements Transaction {
	private int empid;
	private long date;
	private int amount;
	
	PayrollDatabase gpayrollDatabase = PayrollDatabase.gpayrollDatabase;
	
	public AddSalesReceiptTransation() {
		
	}
	
	public AddSalesReceiptTransation(long date, int amount, int empid) {
		this.date = date;
		this.amount = amount;
		this.empid = empid;
	}

	@Override
	public void excute() throws Exception {
		Employee e = gpayrollDatabase.getEmployee(empid);
		if (e != null) {
			PaymentClassification pc = e.getClassification();
			if (pc instanceof CommissionedClassification) {
				CommissionedClassification cc = (CommissionedClassification)pc;
				SalesReceipt sr = new SalesReceipt(date, amount);
				cc.addSalesReceipt(sr);
			}else{
				throw new Exception("tried to add salesReceipt to non-commissioned employee");
			}
		}else{
			throw new Exception("No such employee");
		}

	}

}
