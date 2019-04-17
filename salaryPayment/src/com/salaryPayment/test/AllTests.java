package com.salaryPayment.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ TestAddCommissionedEmployee.class, TestAddHourlyEmployee.class,
		TestAddSalariedEmployee.class, TestAddSalesReceiptTransation.class,
		TestAddServiceCharge.class, TestAddTimeCardTransation.class,
		TestChangeAddressTransation.class,
		TestChangeCommissionedTransation.class,
		TestChangeDirectTransation.class, TestChangeHoldTransation.class,
		TestChangeHourlyTransation.class, TestChangeMailTransation.class,
		TestChangeNameTransation.class, TestChangeSalariedTransation.class,
		TestDeleteEmployee.class })
public class AllTests {

}