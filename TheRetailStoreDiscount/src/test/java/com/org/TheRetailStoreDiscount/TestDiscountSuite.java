package com.org.TheRetailStoreDiscount;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.org.TheRetailStoreDiscount.CalculateCheckoutPrice.FullCostPackage;
import com.org.bean.Affiliate;
import com.org.bean.Customer;
import com.org.bean.Employee;
import com.org.bean.RetailCustomer;
import com.org.configurations.Discounts;
import com.org.retailitems.CartItems;
import com.org.retailitems.RetailItems;
import com.org.retailitems.inventory.BaseInventoryTypes;
import com.org.retailitems.inventory.CartItem;

public class TestDiscountSuite {

	@Test
	public void testEmployeeDiscount() {
		Customer customer = new Employee();

		double discount = CalculateCheckoutPrice.getCustomerDiscountCriteria(customer);

		assertEquals(0.30, discount, 1);
	}

	@Test
	public void testAffiliateDiscount() {
		Customer customer = new Affiliate();
		// customer.setDiscountType(Discounts.DISCOUNT_PERCENTAGE_AFFILIATE);

		double discount = CalculateCheckoutPrice.getCustomerDiscountCriteria(customer);

		assertEquals(0.10, discount, 2);
	}

	@Test
	public void testCustomerRelationship2YearWiseDiscount() {
		Customer customer = new RetailCustomer();

		customer.setRelationshipDays(365 * 2);
		double discount = CalculateCheckoutPrice.getCustomerDiscountCriteria(customer);

		assertEquals(Discounts.DISCOUNT_PERCENTAGE_CUSTOMER_RELATION_YEAR.getDiscount(), discount, 2);
	}

	@Test
	public void testDiscountOnPriceDollarBill() {
		Customer customer = new RetailCustomer();
		customer.setDiscountType(Discounts.DISCOUNT_AMOUNT_ON_PRICE);

		double discount = 0;
		if (customer instanceof RetailCustomer) {
			discount = customer.getDiscountType().getDiscount();
		}

		assertEquals(5.0, discount, 2);
	}

	@Test
	public void testDiscountOnPurchasesForAffiliate() {
		Customer customer = new Affiliate();
		customer.setDiscountType(Discounts.DISCOUNT_PERCENTAGE_AFFILIATE);

		List<RetailItems> retailItems = new ArrayList<>();
		retailItems.add(new CartItems(CartItem.BEETROOT, 100));// 150
		retailItems.add(new CartItems(CartItem.CALCIVITA, 10));// 155

		List<BaseInventoryTypes> omittedInventoryforDiscount = Arrays.asList(BaseInventoryTypes.GROCERY);
		CalculateCheckoutPrice.setOmitDiscountList(omittedInventoryforDiscount);
		FullCostPackage totalPricePackage = CalculateCheckoutPrice.calculateTotalPrice(retailItems, customer);

		assertEquals(305.0, totalPricePackage.getTotalPrice(), 2);
		assertEquals(155 * 0.1, totalPricePackage.getTotalDiscount(), 2);

	}

	@Test
	public void testDiscountOnPurchasesForEmployeeWithGroceryPercentBased() {
		Customer customer = new Employee();
		customer.setDiscountType(Discounts.DISCOUNT_PERCENTAGE_EMPLOYEE);

		List<RetailItems> retailItems = new ArrayList<>();

		retailItems.add(new CartItems(CartItem.BEETROOT, 100));
		retailItems.add(new CartItems(CartItem.CALCIVITA, 10));

		List<BaseInventoryTypes> omittedInventoryforDiscount = Arrays.asList(BaseInventoryTypes.GROCERY);
		CalculateCheckoutPrice.setOmitDiscountList(omittedInventoryforDiscount);

		FullCostPackage totalPricePackage = CalculateCheckoutPrice.calculateTotalPrice(retailItems, customer);
		
		assertEquals(305.5, totalPricePackage.getTotalPrice(), 1);
		assertEquals(155 * 0.3, totalPricePackage.getTotalDiscount(), 2);

	}

	@Test
	public void testDiscountOnPurchasesPer100ForCustomerWithGrocery() {
		Customer customer = new RetailCustomer();
		customer.setRelationshipDays(365 * 2);
		customer.setDiscountType(Discounts.DISCOUNT_AMOUNT_ON_PRICE);

		List<RetailItems> retailItems = new ArrayList<>();
		retailItems.add(new CartItems(CartItem.BEETROOT, 100));// 150
		retailItems.add(new CartItems(CartItem.CALCIVITA, 100));// 1550

		List<BaseInventoryTypes> omittedInventoryforDiscount = Arrays.asList(BaseInventoryTypes.GROCERY);
		CalculateCheckoutPrice.setOmitDiscountList(omittedInventoryforDiscount);

		FullCostPackage totalPricePackage = CalculateCheckoutPrice.calculateTotalPrice(retailItems, customer);

		
		assertEquals(1700.00, totalPricePackage.getTotalPrice(), 2);
		assertEquals(17 * 5, totalPricePackage.getTotalDiscount(), 2);

	}

	@Test
	public void testDiscountonAmountby100() {

		double price = 0.0;
		double discount = CalculateCheckoutPrice.calculateDiscountForAmount(price);

		assertEquals(0.0, discount, 2);
		price = -54.0;
		discount = CalculateCheckoutPrice.calculateDiscountForAmount(price);
		assertEquals(0.0, discount, 2);

		price = 990.0;
		discount = CalculateCheckoutPrice.calculateDiscountForAmount(price);
		assertEquals(45.0, discount, 2);

	}

}
