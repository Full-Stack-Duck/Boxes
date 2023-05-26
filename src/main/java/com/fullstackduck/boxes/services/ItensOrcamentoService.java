package com.fullstackduck.boxes.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.fullstackduck.boxes.entities.ItensOrcamento;
import com.fullstackduck.boxes.repositories.ItensOrcamentoRepository;

@Service //Registro de componente
public class ItensOrcamentoService {

	@Autowired
	private ItensOrcamentoRepository repository;
	
	@Async
	public List<ItensOrcamento> findAll(){
		return repository.findAll();
	}
	
	@Async
	public ItensOrcamento findById(Long id) {
		Optional<ItensOrcamento> obj = repository.findById(id);
		return obj.get();
	}

	@Async
	public ItensOrcamento inserirItensOrcamento(ItensOrcamento obj) {
		return repository.save(obj);
	}
}
