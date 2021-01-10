package com.simpledev;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.jupiter.api.Test;

import com.simpledev.beans.Affiliate;
import com.simpledev.beans.Customer;
import com.simpledev.beans.Employee;
import com.simpledev.beans.RetailCustomer;
import com.simpledev.beans.TotalPriceDetails;
import com.simpledev.config.Discounts;
import com.simpledev.domain.retailitems.CartItems;
import com.simpledev.domain.retailitems.RetailItems;
import com.simpledev.domain.retailitems.inventory.BaseInventoryTypes;
import com.simpledev.domain.retailitems.inventory.CartInventory;
import com.simpledev.services.CalculateCheckoutPriceService;

public class TestDiscountSuite {

	CalculateCheckoutPriceService calculateCheckoutService;
	List<BaseInventoryTypes> omittedInventoryforDiscount =null;
	List<RetailItems> retailItems=null;
	@Before 
	public void setOmittedList() {
		omittedInventoryforDiscount = Arrays.asList(BaseInventoryTypes.GROCERY);
		calculateCheckoutService= new CalculateCheckoutPriceService();
		retailItems = new ArrayList<>();
		//retailItems.add(new CartItems(CartInventory.BEETROOT, 100));// 150
		//retailItems.add(new CartItems(CartInventory.CALCIVITA, 10));// 155
		
	}
	@Test
	public void testEmployeeDiscount() {
		Customer customer = new Employee();

		double discount = calculateCheckoutService.getCustomerDiscountCriteria(customer);

		assertEquals(0.30, discount, 1);
	}

	@Test
	public void testAffiliateDiscount() {
		Customer customer = new Affiliate();
		// customer.setDiscountType(Discounts.DISCOUNT_PERCENTAGE_AFFILIATE);

		double discount = calculateCheckoutService.getCustomerDiscountCriteria(customer);

		assertEquals(0.10, discount, 2);
	}

	@Test
	public void testCustomerRelationship2YearWiseDiscount() {
		Customer customer = new RetailCustomer();

		customer.setRelationshipDays(740);
		double discount = calculateCheckoutService.getCustomerDiscountCriteria(customer);

		assertEquals(Discounts.DISCOUNT_PERCENTAGE_CUSTOMER_RELATION_YEAR.getDiscount(), discount, 2);
	}

	@Test
	public void testDiscountOnPriceDollarBill() {
		Customer customer = new RetailCustomer();
		
		double discount = 0;
		if (customer instanceof RetailCustomer) {
			discount = calculateCheckoutService.getCustomerDiscountCriteria(customer);
		}
		assertEquals(5.0, discount, 2);
	}

	@Test
	public void testDiscountOnPurchasesForAffiliate() {
		Customer customer = new Affiliate();
		
			
		CalculateCheckoutPriceService.setOmitDiscountList(omittedInventoryforDiscount);
		TotalPriceDetails totalPricePackage = calculateCheckoutService.calculateTotalPrice(retailItems, customer);

		assertEquals(305.0, totalPricePackage.getTotalPrice(), 2);
		assertEquals(155 * 0.1, totalPricePackage.getTotalDiscount(), 2);

	}

	@Test
	public void testDiscountOnPurchasesForEmployeeWithGroceryPercentBased() {
		Customer customer = new Employee();

		CalculateCheckoutPriceService.setOmitDiscountList(omittedInventoryforDiscount);

		TotalPriceDetails totalPricePackage = calculateCheckoutService.calculateTotalPrice(retailItems, customer);
		
		assertEquals(305.5, totalPricePackage.getTotalPrice(), 1);
		assertEquals(155 * 0.3, totalPricePackage.getTotalDiscount(), 2);

	}

	@Test
	public void testDiscountOnCustomerOver2YearsRelation() {
		Customer customer = new RetailCustomer();
		customer.setRelationshipDays(365*2+1);
		
		List<RetailItems> retailItems = new ArrayList<>();
		//retailItems.add(new CartItems(CartInventory.BEETROOT, 100));// 150
		//retailItems.add(new CartItems(CartInventory.CALCIVITA, 100));// 1550

		CalculateCheckoutPriceService.setOmitDiscountList(omittedInventoryforDiscount);

		TotalPriceDetails totalPricePackage = calculateCheckoutService.calculateTotalPrice(retailItems, customer);

		
		assertEquals(1700.00, totalPricePackage.getTotalPrice(), 2);
		assertEquals(1550*0.05, totalPricePackage.getTotalDiscount(), 2);

	}
	

	@Test
	public void testDiscountOnRetailCustomerForEach100PricePurchase() {
		Customer customer = new RetailCustomer();
		
		List<RetailItems> retailItems = new ArrayList<>();
	//	retailItems.add(new CartItems(CartInventory.BEETROOT, 100));// 150
		//retailItems.add(new CartItems(CartInventory.CALCIVITA, 100));// 1550

		CalculateCheckoutPriceService.setOmitDiscountList(omittedInventoryforDiscount);

		TotalPriceDetails totalPricePackage = calculateCheckoutService.calculateTotalPrice(retailItems, customer);

		
		assertEquals(1700.00, totalPricePackage.getTotalPrice(), 2);
		assertEquals(17*5, totalPricePackage.getTotalDiscount(), 2);

	}
	
	@Test
	public void testEmployeeDiscounts_On_ManyItems_Percentage() {
		
		Customer customer= new Employee();
		//customer.setUserRelationshipDays(800);		
		
		List<RetailItems> shoppingCart = new ArrayList<>();
		//shoppingCart.add(new CartItems(CartInventory.BEETROOT,100));//1.5*100=150
		//shoppingCart.add(new CartItems(CartInventory.DETTOL,50));//23.5*50=1175.0
		//shoppingCart.add(new CartItems(CartInventory.LEEKS,50));//50*1.5=75.0
		
		TotalPriceDetails totalPrice=calculateCheckoutService.calculateTotalPrice(shoppingCart, customer);
		
		assertEquals(1400.0,totalPrice.getTotalPrice(),2);
		assertEquals(352.5,totalPrice.getTotalDiscount(),2);
		assertEquals(1047.7,totalPrice.getNetPayable(),2);

	}
	
	@Test
	public void testCustomerDiscounts_On_ManyItems_Amount() {
		
		Customer customer= new RetailCustomer();
				
		List<RetailItems> shoppingCart = new ArrayList<>();
		//shoppingCart.add(new CartItems(CartInventory.BEETROOT,100));//1.5*100=150
		//shoppingCart.add(new CartItems(CartInventory.DETTOL,50));//23.5*50=1175.0
		//shoppingCart.add(new CartItems(CartInventory.LEEKS,50));//50*1.5=75.0
		
		TotalPriceDetails totalPrice=calculateCheckoutService.calculateTotalPrice(shoppingCart, customer);
		
		assertEquals(1400.0,totalPrice.getTotalPrice(),2);
		assertEquals(14*5,totalPrice.getTotalDiscount(),2);
		assertEquals(1400-14*5,totalPrice.getNetPayable(),2);

	}

	@Test
	public void testDiscountonAmountby100() {

		double price = 0.0;
		double discount = CalculateCheckoutPriceService.calculateDiscountForAmount(price);

		assertEquals(0.0, discount, 2);
		price = -54.0;
		discount = CalculateCheckoutPriceService.calculateDiscountForAmount(price);
		assertEquals(0.0, discount, 2);

		price = 990.0;
		discount = CalculateCheckoutPriceService.calculateDiscountForAmount(price);
		assertEquals(45.0, discount, 2);

	}


}
