package com.fullstackduck.boxes.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fullstackduck.boxes.entities.MovimentacaoEstoque;
import com.fullstackduck.boxes.repositories.MovimentacaoEstoqueRepository;

@Service //Registro de componente
public class MovimentacaoEstoqueService {

	@Autowired
	private MovimentacaoEstoqueRepository repository;
	
	public List<MovimentacaoEstoque> findAll(){
		return repository.findAll();
	}
	
	public MovimentacaoEstoque findById(Long id) {
		Optional<MovimentacaoEstoque> obj = repository.findById(id);
		return obj.get();
	}
}
