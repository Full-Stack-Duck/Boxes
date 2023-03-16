package com.fullstackduck.boxes.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fullstackduck.boxes.entities.Estoque;
import com.fullstackduck.boxes.repositories.EstoqueRepository;

@Service //Registro de componente
public class EstoqueService {

	@Autowired
	private EstoqueRepository repository;
	
	public List<Estoque> findAll(){
		return repository.findAll();
	}
	
	public Estoque findById(Long id) {
		Optional<Estoque> obj = repository.findById(id);
		return obj.get();
	}
}
