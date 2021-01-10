package com.simpledev.domain.retailitems.inventory;

@Deprecated
public interface BaseInventoryItemList {

		
	long getId();
	
	String getName();

	double getItemPrice();

	public BaseInventoryTypes getInventoryType();
}
