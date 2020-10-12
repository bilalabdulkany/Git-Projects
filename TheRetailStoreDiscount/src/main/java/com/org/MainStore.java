package com.org;

import java.util.ArrayList;
import java.util.List;

import com.org.bean.Customer;
import com.org.bean.Employee;
import com.org.retailitems.CartItems;
import com.org.retailitems.RetailItems;
import com.org.retailitems.inventory.CartInventory;
import com.org.services.CalculateCheckoutPrice;

public class MainStore {

	public static void main(String[] args) {
		MainStore main = new MainStore();
		
		main.runShoppinCart();
	}
	
	public void runShoppinCart() {
		Customer customer= new Employee();
		//customer.setUserRelationshipDays(800);	
		
		List<RetailItems> shoppingCart = new ArrayList<>();
		shoppingCart.add(new CartItems(CartInventory.BEETROOT,100));
		shoppingCart.add(new CartItems(CartInventory.DETTOL,50));
		shoppingCart.add(new CartItems(CartInventory.LEEKS,50));
		
		//TotalPriceDetails totalPrice=CalculateCheckoutPrice.calculateTotalPrice(shoppingCart, customer);
		
		List<RetailItems> shoppingCart1 = new ArrayList<>();
		shoppingCart1.add(new CartItems(CartInventory.LONGBREAD,100));
		shoppingCart1.add(new CartItems(CartInventory.CALCIVITA,50));
		shoppingCart1.add(new CartItems(CartInventory.GLOVES,50));
		
		//TotalPriceDetails totalPrice1=CalculateCheckoutPrice.calculateTotalPrice(shoppingCart1, customer);
		
		
		System.out.println(CalculateCheckoutPrice.calculateTotalPrice(shoppingCart, customer));	
		System.out.println(CalculateCheckoutPrice.calculateTotalPrice(shoppingCart1, customer));	
	}

}
