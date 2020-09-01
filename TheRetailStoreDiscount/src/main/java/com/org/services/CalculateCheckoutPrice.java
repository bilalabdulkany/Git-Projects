package com.org.services;

import java.util.List;

import com.org.bean.Affiliate;
import com.org.bean.Customer;
import com.org.bean.Employee;
import com.org.bean.RetailCustomer;
import com.org.bean.TotalPriceDetails;
import com.org.configurations.DiscountMethod;
import com.org.configurations.Discounts;
import com.org.retailitems.RetailItems;
import com.org.retailitems.inventory.BaseInventoryTypes;
import com.org.retailitems.inventory.DiscountExclusionList;

public final class CalculateCheckoutPrice {

	private static List<BaseInventoryTypes> omittedList = DiscountExclusionList.omittedInventoryforDiscount;

	public static void setOmitDiscountList(List<BaseInventoryTypes> omittedInventoryItemsForDiscount) {
		omittedList = omittedInventoryItemsForDiscount;
	}

	static TotalPriceDetails pricePackage;

	public static double getCustomerDiscountCriteria(Customer customer) {
		double eligibleDiscount = 0.0;

		if (customer.getDiscountType() == null) {
			if (customer instanceof Employee) {

				customer.setDiscountType(Discounts.DISCOUNT_PERCENTAGE_EMPLOYEE);
				customer.setDiscount(Discounts.DISCOUNT_PERCENTAGE_EMPLOYEE.getDiscount());
				
				eligibleDiscount = customer.getDiscount();

			} else if (customer instanceof Affiliate) {

				customer.setDiscountType(Discounts.DISCOUNT_PERCENTAGE_AFFILIATE);
				customer.setDiscount(Discounts.DISCOUNT_PERCENTAGE_AFFILIATE.getDiscount());
				eligibleDiscount = customer.getDiscount();

			} else if (customer instanceof RetailCustomer) {

				if (customer.getUserRelationshipDays() > 365 * 2) {
					customer.setDiscountType(Discounts.DISCOUNT_PERCENTAGE_CUSTOMER_RELATION_YEAR);
					customer.setDiscount(Discounts.DISCOUNT_PERCENTAGE_CUSTOMER_RELATION_YEAR.getDiscount());
					eligibleDiscount = customer.getDiscount();
				}else {
					customer.setDiscountType(Discounts.DISCOUNT_AMOUNT_ON_PRICE);
					customer.setDiscount(Discounts.DISCOUNT_AMOUNT_ON_PRICE.getDiscount());
					eligibleDiscount = customer.getDiscount();
				
				}
			}
		} else {
			eligibleDiscount = customer.getDiscountType().getDiscount();
		}

		return eligibleDiscount;
	}

	public static TotalPriceDetails calculateTotalPrice(List<RetailItems> allItemList, Customer customer) {

		pricePackage = new TotalPriceDetails();
		double totalPrice = 0.0;
		double qtyPrice = 0.0;
		double qtyDiscount = 0.0;
		double totalDiscount = 0.0;
		double reducedDiscountAmountforOmittedItems = 0.0;
		qtyDiscount = getCustomerDiscountCriteria(customer);
		for (RetailItems item : allItemList) {

			qtyPrice = item.getBaseInventoryList().getItemPrice() * item.getQuantity();
			totalPrice += qtyPrice;
			
			if (!omittedList.isEmpty()&&customer.getDiscountType()!=null&&customer.getDiscountType().getDiscountMethod()==DiscountMethod.PERCENTAGE) {				
				
				for (BaseInventoryTypes baseOmittedList : omittedList) {
					if (!item.getBaseInventoryList().getInventoryType().equals(baseOmittedList)) {
						
						//reducedDiscountAmountforOmittedItems=qtyPrice;
						reducedDiscountAmountforOmittedItems=+qtyPrice;
					}
					
				}
			}
			
			//qtyDiscount += reducedDiscountAmountforOmittedItems * qtyDiscount;

		}
		if (customer.getDiscountType().equals(Discounts.DISCOUNT_AMOUNT_ON_PRICE)) {
			qtyDiscount = calculateDiscountForAmount(totalPrice);
			totalDiscount = qtyDiscount;
		}
		else {
			qtyDiscount=customer.getDiscountType().getDiscount();
			if(customer.getDiscountType().getDiscountMethod().equals(DiscountMethod.PERCENTAGE))
			totalDiscount=reducedDiscountAmountforOmittedItems*qtyDiscount;
			else
				totalDiscount=totalPrice*qtyDiscount;
		}

		pricePackage.setTotalPrice(totalPrice);
		pricePackage.setTotalDiscount(totalDiscount);
		pricePackage.setDiscountPercentage(qtyDiscount);
		pricePackage.setDiscountType(customer.getDiscountType());
		return pricePackage;
	}

	public static double calculateDiscountForAmount(double price) {
		double discount = 0.0;
		if (price > 0) {

			discount = Math.floor(price / Discounts.DISCOUNT_AMOUNT_RANGE.getDiscount());
			discount = discount * Discounts.DISCOUNT_AMOUNT_ON_PRICE.getDiscount();

		}
		return discount;
	}

	

}
