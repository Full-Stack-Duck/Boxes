package com.fullstackduck.boxes.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fullstackduck.boxes.entities.ItensOrcamento;
import com.fullstackduck.boxes.repositories.ItensOrcamentoRepository;

@Service //Registro de componente
public class ItensOrcamentoService {

	@Autowired
	private ItensOrcamentoRepository repository;
	
	public List<ItensOrcamento> findAll(){
		return repository.findAll();
	}
	
	public ItensOrcamento findById(Long id) {
		Optional<ItensOrcamento> obj = repository.findById(id);
		return obj.get();
	}

	//insere orcamento no banco de dados
	public ItensOrcamento inserirItensOrcamento(ItensOrcamento obj) {
		return repository.save(obj);
	}
}
