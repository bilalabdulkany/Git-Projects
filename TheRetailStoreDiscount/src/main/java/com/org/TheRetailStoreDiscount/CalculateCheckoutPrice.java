package com.org.TheRetailStoreDiscount;

import java.util.ArrayList;
import java.util.List;

import com.org.bean.Affiliate;
import com.org.bean.Customer;
import com.org.bean.Employee;
import com.org.bean.RetailCustomer;
import com.org.configurations.DiscountMethod;
import com.org.configurations.Discounts;
import com.org.retailitems.RetailItems;
import com.org.retailitems.inventory.BaseInventoryTypes;

final class CalculateCheckoutPrice {

	private static List<BaseInventoryTypes> omittedList = new ArrayList<>();

	public static void setOmitDiscountList(List<BaseInventoryTypes> omittedInventoryItemsForDiscount) {
		omittedList = omittedInventoryItemsForDiscount;
	}

	static FullCostPackage pricePackage;

	public static double getCustomerDiscountCriteria(Customer customer) {
		double eligibleDiscount = 0.0;

		if (customer.getDiscountType() == null) {
			if (customer instanceof Employee) {

				customer.setDiscount(Discounts.DISCOUNT_PERCENTAGE_EMPLOYEE.getDiscount());
				eligibleDiscount = customer.getDiscount();

			} else if (customer instanceof Affiliate) {

				customer.setDiscount(Discounts.DISCOUNT_PERCENTAGE_AFFILIATE.getDiscount());
				eligibleDiscount = customer.getDiscount();

			} else if (customer instanceof RetailCustomer) {

				if (customer.getUserRelationshipDays() == 365 * 2) {
					customer.setDiscount(Discounts.DISCOUNT_PERCENTAGE_CUSTOMER_RELATION_YEAR.getDiscount());
					eligibleDiscount = customer.getDiscount();
				}
			}
		} else {
			eligibleDiscount = customer.getDiscountType().getDiscount();
		}

		return eligibleDiscount;
	}

	public static FullCostPackage calculateTotalPrice(List<RetailItems> allItemList, Customer customer) {

		pricePackage = new FullCostPackage();
		double totalPrice = 0.0;
		double qtyPrice = 0.0;
		double qtyDiscount = customer.getDiscountType().getDiscount();
		double totalDiscount = 0.0;
		double reducedDiscountAmountforOmittedItems = 0.0;
		qtyDiscount = customer.getDiscountType().getDiscount();
		for (RetailItems item : allItemList) {

			qtyPrice = item.getBaseInventoryList().getItemPrice() * item.getQuantity();
			totalPrice += qtyPrice;
			if (!omittedList.isEmpty()&&customer.getDiscountType().getDiscountMethod()==DiscountMethod.PERCENTAGE) {				
				for (BaseInventoryTypes baseOmittedList : omittedList) {
					if (item.getBaseInventoryList().getInventoryType().equals(baseOmittedList)) {
						// calculateNoDiscount
						reducedDiscountAmountforOmittedItems = qtyPrice;
						qtyDiscount = 0;
						if (totalPrice >= reducedDiscountAmountforOmittedItems) {
							reducedDiscountAmountforOmittedItems = totalPrice - reducedDiscountAmountforOmittedItems;
						}
					} else {
						reducedDiscountAmountforOmittedItems += qtyPrice;
					}
				}
			}else {
				reducedDiscountAmountforOmittedItems=+totalPrice;
			}
			//qtyDiscount += reducedDiscountAmountforOmittedItems * qtyDiscount;

		}
		if (customer.getDiscountType().equals(Discounts.DISCOUNT_AMOUNT_ON_PRICE)) {
			qtyDiscount = calculateDiscountForAmount(reducedDiscountAmountforOmittedItems);
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

	static class FullCostPackage {

		public FullCostPackage() {

		}

		private double totalPrice;
		private double totalDiscount;

		public double getTotalPrice() {
			return totalPrice;
		}

		public void setTotalPrice(double totalPrice) {
			this.totalPrice = totalPrice;
		}

		public double getTotalDiscount() {
			return totalDiscount;
		}

		public void setTotalDiscount(double totalDiscount) {
			this.totalDiscount = totalDiscount;
		}

	}

}
