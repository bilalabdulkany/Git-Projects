package com.simpledev.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

import com.simpledev.beans.Customer;
import com.simpledev.beans.RetailCustomer;
import com.simpledev.beans.TotalPriceDetails;
import com.simpledev.domain.retailitems.CartItemBean;
import com.simpledev.domain.retailitems.RetailItems;
import com.simpledev.repository.CartItemRepository;
import com.simpledev.services.CalculateCheckoutPriceService;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.NONE)
public class CalculatePriceServiceIntegrationTest {

	@Autowired
	CalculateCheckoutPriceService calculateService;
	
	@Autowired
	CartItemRepository repo;
	
	@Test
	public void testAddCartItem() throws Exception {
		
		repo.deleteAll();
		
		RetailItems cartBean= new RetailItems();
		cartBean.setId("1");
		cartBean.setName("Beans");
				
		List<RetailItems> items = new ArrayList<>();
		items.add(cartBean);
		
		RetailItems bean = calculateService.updateCartItemQty(cartBean);	
		assertNotNull(bean);
		
	}
	
	@Test
	public void testUpdateItemQty() throws Exception {
		repo.deleteAll();

		RetailItems cartBean= new RetailItems();
		cartBean.setId("1");
		cartBean.setName("Beans");
		cartBean.setQuantity(3);
		
		RetailItems bean = calculateService.updateCartItemQty(cartBean);
		
		cartBean.setQuantity(1);
		bean = calculateService.updateCartItemQty(cartBean);
		assertEquals(5.0,bean.getQuantity(),2);
	}
	
	@Test
	public void calculateTotalTest() {
		Customer customer= new RetailCustomer();
		
		List<RetailItems> shoppingCart = new ArrayList<>();
		shoppingCart.add(new RetailItems("1","Beans",100.0));//1.5*100=150
		shoppingCart.add(new RetailItems("2","Dettol",50.0));//23.5*50=1175.0
		shoppingCart.add(new RetailItems("3","Leaks",50.0));//50*1.5=75.0
		
		TotalPriceDetails totalPrice=calculateService.calculateTotalPrice(shoppingCart, customer);
		
		assertEquals(1400.0,totalPrice.getTotalPrice(),2);
		assertEquals(14*5,totalPrice.getTotalDiscount(),2);
		assertEquals(1400-14*5,totalPrice.getNetPayable(),2);
	}
}
