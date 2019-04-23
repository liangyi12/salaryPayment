package com.salaryPayment.affiliation;

import com.salaryPayment.domain.Paycheck;

public class NoAffiliation implements Affiliation {

	@Override
	public double calculateDeductions(Paycheck pc) {
		return 0;
	}

}
