package com.simpledev.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.simpledev.domain.retailitems.RetailItems;

@Repository
public interface CartItemRepository extends CrudRepository<RetailItems, String>{

}
