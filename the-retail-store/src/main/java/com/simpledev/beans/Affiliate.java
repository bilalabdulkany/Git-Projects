package com.simpledev.beans;

import com.simpledev.config.Discounts;

public class Affiliate extends Customer {

	public Affiliate(){
		this.setDiscountType(Discounts.DISCOUNT_PERCENTAGE_AFFILIATE);
		this.setDiscount(Discounts.DISCOUNT_PERCENTAGE_AFFILIATE.getDiscount());

	}

}
