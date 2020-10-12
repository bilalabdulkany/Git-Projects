package com.org.bean;

import com.org.configurations.Discounts;

public class Employee extends Customer {
		
	public Employee() {
		
		this.setDiscountType(Discounts.DISCOUNT_PERCENTAGE_EMPLOYEE);
		this.setDiscount(Discounts.DISCOUNT_PERCENTAGE_EMPLOYEE.getDiscount());
		
	}

}
