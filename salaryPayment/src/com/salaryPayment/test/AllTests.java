package com.salaryPayment.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ TestAddCommissionedEmployee.class, TestAddHourlyEmployee.class,
		TestAddSalariedEmployee.class, TestAddServiceCharge.class,
		TestDeleteEmployee.class, TestSalesReceiptTransation.class,
		TestTimeCardTransation.class })
public class AllTests {

}
