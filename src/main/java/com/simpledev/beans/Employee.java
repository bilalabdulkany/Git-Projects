package com.simpledev.beans;

import com.simpledev.config.Discounts;

public class Employee extends Customer {
		
	public Employee() {
		
		this.setDiscountType(Discounts.DISCOUNT_PERCENTAGE_EMPLOYEE);
		this.setDiscount(Discounts.DISCOUNT_PERCENTAGE_EMPLOYEE.getDiscount());
		
	}

}
