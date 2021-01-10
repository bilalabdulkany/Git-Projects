package com.simpledev.services;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.simpledev.beans.Customer;
import com.simpledev.beans.TotalPriceDetails;
import com.simpledev.config.DiscountMethod;
import com.simpledev.config.Discounts;
import com.simpledev.domain.retailitems.CartItemBean;
import com.simpledev.domain.retailitems.RetailItems;
import com.simpledev.domain.retailitems.inventory.BaseInventoryTypes;
import com.simpledev.domain.retailitems.inventory.DiscountExclusionList;
import com.simpledev.repository.CartItemRepository;
import com.simpledev.repository.RetailInventoryRepository;

@Service
public final class CalculateCheckoutPriceService {

	@Autowired
	private CartItemRepository cartItemRepo;
	
	@Autowired
	private RetailInventoryRepository inventoryRepo;

	private static List<BaseInventoryTypes> omittedList = DiscountExclusionList.omittedInventoryforDiscount;

	public static void setOmitDiscountList(List<BaseInventoryTypes> omittedInventoryItemsForDiscount) {
		omittedList = omittedInventoryItemsForDiscount;
	}

	public double getCustomerDiscountCriteria(Customer customer) {
		double eligibleDiscount = 0.0;

		if (customer.getDiscountType() == null) {
			eligibleDiscount = customer.getDiscount();

		} else {
			eligibleDiscount = customer.getDiscountType().getDiscount();
		}

		return eligibleDiscount;
	}
	
	public TotalPriceDetails getTotalPriceForCheckout(Customer customer) {
		
				Iterable<RetailItems> iterable= cartItemRepo.findAll();
				List<RetailItems> allItemList = StreamSupport.stream(iterable.spliterator(), false)
						.collect(Collectors.toList());
				return calculateTotalPrice(allItemList,customer);
				
	}

	public TotalPriceDetails calculateTotalPrice(List<RetailItems> allItemList, Customer customer) {

		TotalPriceDetails pricePackage = TotalPriceDetails.getTotalPriceInstance();
		double totalPrice = 0.0;
		double qtyPrice = 0.0;
		double qtyDiscount = 0.0;
		double totalDiscount = 0.0;
		double reducedDiscountAmountforOmittedItems = 0.0;
		qtyDiscount = getCustomerDiscountCriteria(customer);
		for (RetailItems item : allItemList) {
			qtyPrice = inventoryRepo.findPriceById(item.getId())*item.getQuantity();
			totalPrice += qtyPrice;

			if (!omittedList.isEmpty() && customer.getDiscountType() != null
					&& customer.getDiscountType().getDiscountMethod() == DiscountMethod.PERCENTAGE) {

				for (BaseInventoryTypes baseOmittedList : omittedList) {
					if (!item.getBaseInventoryList().getInventoryType().equals(baseOmittedList)) {
						reducedDiscountAmountforOmittedItems = +qtyPrice;
					}
				}
			}
		}
		if (customer.getDiscountType().equals(Discounts.DISCOUNT_AMOUNT_ON_PRICE)) {
			qtyDiscount = calculateDiscountForAmount(totalPrice);
			totalDiscount = qtyDiscount;
		} else {
			qtyDiscount = customer.getDiscountType().getDiscount();
			if (customer.getDiscountType().getDiscountMethod().equals(DiscountMethod.PERCENTAGE))
				totalDiscount = reducedDiscountAmountforOmittedItems * qtyDiscount;
			else
				totalDiscount = totalPrice * qtyDiscount;
		}

		pricePackage.setTotalPrice(totalPrice);
		pricePackage.setTotalDiscount(totalDiscount);
		pricePackage.setDiscountPercentage(qtyDiscount);
		pricePackage.setDiscountType(customer.getDiscountType());
		return pricePackage;
	}

	public static double calculateDiscountForAmount(double price) {
		double discount = 0.0;
		if (price > 0) {

			discount = Math.floor(price / Discounts.DISCOUNT_AMOUNT_RANGE.getDiscount());
			discount = discount * Discounts.DISCOUNT_AMOUNT_ON_PRICE.getDiscount();

		}
		return discount;
	}



	public RetailItems updateCartItemQty(RetailItems cartBean) {
		RetailItems beanItem = cartItemRepo.findById(cartBean.getId()).orElse(null);
		double newQty = 0.0;
		if (beanItem != null) {
			newQty = beanItem.getQuantity() + cartBean.getQuantity();
			if(newQty>=0) {				
			beanItem.setQuantity(newQty);
			beanItem= cartItemRepo.save(beanItem);
			}
			
		}else {
			beanItem= cartItemRepo.save(cartBean);
		}
		return beanItem;

	}

}
