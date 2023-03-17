package com.fullstackduck.boxes.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fullstackduck.boxes.entities.Pagamento;
import com.fullstackduck.boxes.repositories.PagamentoRepository;

@Service //Registro de componente
public class PagamentoService {

	@Autowired
	private PagamentoRepository repository;
	
	public List<Pagamento> findAll(){
		return repository.findAll();
	}
	
	public Pagamento findById(Long id) {
		Optional<Pagamento> obj = repository.findById(id);
		return obj.get();
	}
}
