package com.simpledev.service;

import java.util.Optional;

import com.simpledev.beans.Foo;

public interface IFooService {
	Optional<Foo> findById(Long id);
	 
    Foo save(Foo foo);
    
    Iterable<Foo> findAll();
}
