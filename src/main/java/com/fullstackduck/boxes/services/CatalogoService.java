package com.fullstackduck.boxes.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fullstackduck.boxes.entities.Catalogo;
import com.fullstackduck.boxes.repositories.CatalogoRepository;

@Service //Registro de componente
public class CatalogoService {

	@Autowired
	private CatalogoRepository repository;
	
	public List<Catalogo> findAll(){
		return repository.findAll();
	}
	
	public Catalogo findById(Long id) {
		Optional<Catalogo> obj = repository.findById(id);
		return obj.get();
	}
}
