package com.org.configurations;

public enum Discounts {

	DISCOUNT_PERCENTAGE_EMPLOYEE(0.30,DiscountMethod.PERCENTAGE),
	DISCOUNT_PERCENTAGE_AFFILIATE(0.1,DiscountMethod.PERCENTAGE),
	DISCOUNT_PERCENTAGE_CUSTOMER_RELATION_YEAR(0.05,DiscountMethod.PERCENTAGE),
	DISCOUNT_AMOUNT_ON_PRICE(5,DiscountMethod.AMOUNT),
	DISCOUNT_AMOUNT_RANGE(100,DiscountMethod.AMOUNT);
	
	private double discount=0.0;
	private DiscountMethod discountMethod;
	
	Discounts(double discount, DiscountMethod discountMethod){
		this.discount=discount;
		this.discountMethod=discountMethod;
		
	}

	public DiscountMethod getDiscountMethod() {
		return discountMethod;
	}

	public void setDiscountMethod(DiscountMethod discountMethod) {
		this.discountMethod = discountMethod;
	}

	public double getDiscount() {
		return discount;
	}

	
}

