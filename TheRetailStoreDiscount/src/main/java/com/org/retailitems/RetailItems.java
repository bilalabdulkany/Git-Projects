package com.org.retailitems;



import com.org.retailitems.inventory.BaseInventoryItemList;

public abstract class RetailItems {

	private BaseInventoryItemList baseInventoryList;
	private int quantity;
	public RetailItems(BaseInventoryItemList inventoryItem, int quantity) {
		this.baseInventoryList=inventoryItem;
		this.quantity=quantity;
	}
	public BaseInventoryItemList getBaseInventoryList() {
		return baseInventoryList;
	}
	public void setBaseInventoryList(BaseInventoryItemList baseInventoryList) {
		this.baseInventoryList = baseInventoryList;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}


}
