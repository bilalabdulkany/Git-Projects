package com.org.retailitems;



import com.org.retailitems.inventory.BaseInventoryItemList;

public abstract class RetailItems {

	private BaseInventoryItemList baseInventoryList;
	private double quantity;
	public RetailItems(BaseInventoryItemList inventoryItem, double quantity) {
		this.baseInventoryList=inventoryItem;
		this.quantity=quantity;
	}
	public BaseInventoryItemList getBaseInventoryList() {
		return baseInventoryList;
	}

	public double getQuantity() {
		return quantity;
	}



}
