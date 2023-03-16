package com.fullstackduck.boxes.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fullstackduck.boxes.entities.Orcamento;
import com.fullstackduck.boxes.repositories.OrcamentoRepository;

@Service //Registro de componente
public class OrcamentoService {

	@Autowired
	private OrcamentoRepository repository;
	
	public List<Orcamento> findAll(){
		return repository.findAll();
	}
	
	public Orcamento findById(Long id) {
		Optional<Orcamento> obj = repository.findById(id);
		return obj.get();
	}
}
