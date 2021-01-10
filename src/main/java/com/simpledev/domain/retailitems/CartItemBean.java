package com.simpledev.domain.retailitems;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import org.springframework.data.annotation.Id;

import com.simpledev.domain.retailitems.inventory.BaseInventoryTypes;



@Entity
@Deprecated
public class CartItemBean extends RetailItems {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	

	private BaseInventoryTypes inventoryType;

	public CartItemBean() {
		// TODO Auto-generated constructor stub
	}



	public CartItemBean(long id, String name, double qty) {
		this.id=id;
		this.setName(name);
		this.setQuantity(qty);
	}

	

}
