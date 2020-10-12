package com.org.bean;

import com.org.configurations.Discounts;

public abstract class Customer {

	protected double userDiscountPercentage=0.0;
	private Discounts discount;
	private int userRelationshipDays;

	public Discounts getDiscountType() {
		// TODO Auto-generated method stub
		return this.discount;
	}
	public void setDiscountType(Discounts discount) {
		// TODO Auto-generated method stub
		this.discount=discount;
	}

	public void setDiscount(double userDiscountPercentage) {
		this.userDiscountPercentage=userDiscountPercentage;
		
	}
	
	public double getDiscount() {
		return this.userDiscountPercentage;
		
	}

	public void setRelationshipDays(int relativeDays) {
		this.userRelationshipDays=relativeDays;
		
	}

	public int getUserRelationshipDays() {
		return userRelationshipDays;
	}

	public void setUserRelationshipDays(int userRelationshipDays) {
		this.userRelationshipDays = userRelationshipDays;
	}
	

}
