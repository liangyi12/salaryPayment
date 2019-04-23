package com.salaryPayment.payroll;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.salaryPayment.database.PayrollDatabase;
import com.salaryPayment.domain.Employee;
import com.salaryPayment.domain.Paycheck;
import com.salaryPayment.transaction.add.AddHourlyEmployee;
import com.salaryPayment.transaction.add.AddSalariedEmployee;
import com.salaryPayment.transaction.add.AddTimeCardTransation;
import com.salaryPayment.transaction.change.ChangeHourlyTransation;
import com.salaryPayment.transaction.pay.PayDayTransation;



public class Payroll {
	PayrollDatabase gpayrollDatabase = PayrollDatabase.gpayrollDatabase;
	
	public void parse(String[][] commands) throws Exception{
		//你的代码， 当然你需要创建各种各样的类来实现业务逻辑		
		for(int i = 0; i < commands.length; i++) {
			String operation = commands[i][0];
			switch(operation) {
			case "AddMonthlyEmployee":
				AddSalariedEmployee ast = new AddSalariedEmployee(Integer.parseInt(commands[i][1]), commands[i][2], 
						commands[i][3], Double.parseDouble(commands[i][4]));
				ast.excute();
				break;
			case "AddHourlyEmployee" : 
				AddHourlyEmployee aht = new AddHourlyEmployee(Integer.parseInt(commands[i][1]), commands[i][2], 
						commands[i][3], Double.parseDouble(commands[i][4]));
				aht.excute();
				break;
			case "AddTimeCard" : 
				AddTimeCardTransation act = new AddTimeCardTransation(parseDate(commands[i][2]), Double.parseDouble(commands[i][3]), Integer.parseInt(commands[i][1]));
				act.excute();
				break;
			case "ChangeToHourlyEmployee" : 
				ChangeHourlyTransation cht = new ChangeHourlyTransation(Integer.parseInt(commands[i][1]), Double.parseDouble(commands[i][2]));
				cht.excute();
				break;
			}
		}
	}
	
	
	public Map<String,Double> payFor(Date d) throws Exception{
		Map<String, Double> payments = new HashMap<String, Double>();
		PayDayTransation pt = new PayDayTransation(d);
		pt.excute();
		

		
		for (Employee e : gpayrollDatabase.getEmployees()) {
			int empid = e.getEmpid();
			Paycheck pc = pt.getPayCheck(empid);
			if (pc != null ) {
				payments.put("" + empid, pc.getNetPay());
			}	
		}
		
		return payments;
	}
	
	public Date parseDate(String s) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.parse(s);
	}
}
