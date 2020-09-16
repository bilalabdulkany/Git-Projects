package com.simpledev.service;

import java.util.Optional;

import com.simpledev.beans.Foo;
import com.simpledev.repository.IFooRepository;

public class IFooServiceImpl implements IFooService{

	private IFooRepository fooRepository;
	
	public IFooServiceImpl(IFooRepository fooRepository) {
        this.fooRepository = fooRepository;
    }
	
	@Override
	public Optional<Foo> findById(Long id) {
		// TODO Auto-generated method stub
		return fooRepository.findById(id);
	}

	@Override
	public Foo save(Foo foo) {
		// TODO Auto-generated method stub
		return fooRepository.save(foo);
	}

	@Override
	public Iterable<Foo> findAll() {
		// TODO Auto-generated method stub
		return fooRepository.findAll();
	}

}
