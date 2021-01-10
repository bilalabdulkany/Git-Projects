package com.simpledev.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.simpledev.domain.retailitems.inventory.InventoryItems;
@Repository
public interface RetailInventoryRepository extends CrudRepository<InventoryItems, String> {

	
	double findPriceById(String id);
}
