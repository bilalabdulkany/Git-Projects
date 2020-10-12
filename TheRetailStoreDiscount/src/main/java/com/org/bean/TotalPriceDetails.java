package com.org.bean;

import com.org.configurations.Discounts;

public final class TotalPriceDetails {

	private static TotalPriceDetails totalPriceDetailsInstance;

	private double totalPrice;
	private double totalDiscount;
	private Discounts discountType;
	private double discountPercentage;

	private TotalPriceDetails() {

	}

	public static TotalPriceDetails getTotalPriceInstance() {
		/**
		 * Double instantiation check for multithreaded environment
		 */
		if (totalPriceDetailsInstance == null) {
			synchronized (TotalPriceDetails.class) {
				if (totalPriceDetailsInstance == null) {
					totalPriceDetailsInstance = new TotalPriceDetails();
				}
			}
		}
		return totalPriceDetailsInstance;
	}

	public double getDiscountPercentage() {
		return discountPercentage;
	}

	public void setDiscountPercentage(double discountPercentage) {
		this.discountPercentage = discountPercentage;
	}

	public Discounts getDiscountType() {
		return discountType;
	}

	public void setDiscountType(Discounts discountType) {
		this.discountType = discountType;
	}

	public double getNetPayable() {
		return totalPrice - totalDiscount;
	}

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

	@Override
	public String toString() {
		return "TotalPriceDetails [Gross Price=" + totalPrice + ", Total Discount=" + totalDiscount + ", Discount Type="
				+ discountType + ", Discounted Percentage=" + discountPercentage + ", Net Amount=" + getNetPayable()
				+ "]";
	}

}
