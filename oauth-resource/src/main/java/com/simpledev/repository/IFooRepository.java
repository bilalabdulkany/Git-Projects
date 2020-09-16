package com.simpledev.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.simpledev.beans.Foo;
public interface IFooRepository extends PagingAndSortingRepository<Foo,Long>{

}
