package com.org.retailitems.inventory;

public class CardInventoryDBAdapter implements BaseInventoryItemList {
	
private double itemPrice;
	
	private BaseInventoryTypes inventoryType;

	public CardInventoryDBAdapter(double price,BaseInventoryTypes inventoryType) {
		this.itemPrice=price;
		this.inventoryType=inventoryType;
	}

	@Override
	public double getItemPrice() {
		// TODO Auto-generated method stub
		return this.itemPrice;
	}

	@Override
	public BaseInventoryTypes getInventoryType() {
		// TODO Auto-generated method stub
		return this.inventoryType;
	}

}
