package com.fullstackduck.boxes.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fullstackduck.boxes.entities.Despesa;
import com.fullstackduck.boxes.repositories.DespesaRepository;

@Service //Registro de componente
public class DespesaService {

	@Autowired
	private DespesaRepository repository;
	
	public List<Despesa> findAll(){
		return repository.findAll();
	}
	
	public Despesa findById(Long id) {
		Optional<Despesa> obj = repository.findById(id);
		return obj.get();
	}
}
