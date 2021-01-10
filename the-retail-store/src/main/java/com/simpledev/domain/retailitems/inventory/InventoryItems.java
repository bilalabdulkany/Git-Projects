package com.simpledev.domain.retailitems.inventory;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class InventoryItems {
	@Id
	private String id;
	@Indexed
	private String itemName;
	private double itemPrice;
	private double inventoryQty;	
	private BaseInventoryTypes inventoryType;
	
	
	public InventoryItems() {
	
	}

	public double getInventoryQty() {
		return inventoryQty;
	}

	public void setInventoryQty(double inventoryQty) {
		this.inventoryQty = inventoryQty;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public double getItemPrice() {
		return itemPrice;
	}

	public void setItemPrice(double itemPrice) {
		this.itemPrice = itemPrice;
	}

	public BaseInventoryTypes getInventoryType() {
		return inventoryType;
	}

	public void setInventoryType(BaseInventoryTypes inventoryType) {
		this.inventoryType = inventoryType;
	}

}
