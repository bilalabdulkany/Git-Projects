package com.simpledev.domain.retailitems;



import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import com.simpledev.domain.retailitems.inventory.InventoryItems;

@Document
public class RetailItems {

	private InventoryItems inventoryItem;
	@Id
	private String id;
	@Indexed
	private String name;
	//private double itemPrice;
	private double quantity;
	
	public RetailItems() {
		
	}
	
	public RetailItems(String id, String name, double quantity) {
		this.id = id;
		this.name = name;
		this.quantity = quantity;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	/*
	 * public void setItemPrice(double itemPrice) { this.itemPrice = itemPrice; }
	 */
	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}
	public InventoryItems getBaseInventoryList() {
		return inventoryItem;
	}

	public double getQuantity() {
		return quantity;
	}
	/*
	 * public double getItemPrice() { // TODO Auto-generated method stub return
	 * this.itemPrice; }
	 */



}
