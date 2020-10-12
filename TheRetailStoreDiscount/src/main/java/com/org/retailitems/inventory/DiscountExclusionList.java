package com.org.retailitems.inventory;

import java.util.Arrays;
import java.util.List;

public interface DiscountExclusionList {

	static List<BaseInventoryTypes> omittedInventoryforDiscount =
			Arrays.asList(BaseInventoryTypes.GROCERY);
}
