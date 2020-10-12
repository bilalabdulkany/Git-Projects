package com.org.bean;

import com.org.configurations.Discounts;

public class RetailCustomer extends Customer {
	
	public RetailCustomer() {
		
			this.setDiscountType(Discounts.DISCOUNT_AMOUNT_ON_PRICE);
			this.setDiscount(Discounts.DISCOUNT_AMOUNT_ON_PRICE.getDiscount());			
		
	}

	@Override
	public void setRelationshipDays(int relativeDays) {
		if (relativeDays> 365 * 2) {
			this.setDiscountType(Discounts.DISCOUNT_PERCENTAGE_CUSTOMER_RELATION_YEAR);
			this.setDiscount(Discounts.DISCOUNT_PERCENTAGE_CUSTOMER_RELATION_YEAR.getDiscount());
			
		}else {
			this.setDiscountType(Discounts.DISCOUNT_AMOUNT_ON_PRICE);
			this.setDiscount(Discounts.DISCOUNT_AMOUNT_ON_PRICE.getDiscount());			
		
		}
		super.setRelationshipDays(relativeDays);
	}
	
	

}
