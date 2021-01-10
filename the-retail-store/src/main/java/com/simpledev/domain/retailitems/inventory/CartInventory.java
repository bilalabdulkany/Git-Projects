package com.simpledev.domain.retailitems.inventory;

@Deprecated
public enum CartInventory implements BaseInventoryItemList {
	BREAD(2.5,BaseInventoryTypes.GROCERY), LONGBREAD(3.5,BaseInventoryTypes.GROCERY),
	CARROT(1.5,BaseInventoryTypes.GROCERY), BEETROOT(1.5,BaseInventoryTypes.GROCERY),
	LEEKS(1.5,BaseInventoryTypes.GROCERY), ONIONS(1.5,BaseInventoryTypes.GROCERY),
	
	FACEMASK(6.5,BaseInventoryTypes.PHARMACY),GLOVES(7.5,BaseInventoryTypes.PHARMACY),
	DETTOL(23.5,BaseInventoryTypes.PHARMACY),
	CALCIVITA(15.5,BaseInventoryTypes.PHARMACY);

	
	private double itemPrice;
	
	private BaseInventoryTypes inventoryType;
	
	CartInventory(double price,BaseInventoryTypes inventoryType) {
		this.itemPrice=price;
		this.inventoryType=inventoryType;
	}
	@Override
	public double getItemPrice() {
		// TODO Auto-generated method stub
		return itemPrice;
	}

	@Override
	public BaseInventoryTypes getInventoryType() {
		// TODO Auto-generated method stub
		return inventoryType;
	}
	@Override
	public long getId() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

}
