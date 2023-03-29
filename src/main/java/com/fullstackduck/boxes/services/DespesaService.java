package com.fullstackduck.boxes.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fullstackduck.boxes.entities.Despesa;
import com.fullstackduck.boxes.repositories.DespesaRepository;
import com.fullstackduck.boxes.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service //Registro de componente
public class DespesaService {

	@Autowired
	private DespesaRepository repository;
	
	@Transactional
	public List<Despesa> findAll(){
		return repository.findAll();
	}
	
	@Transactional
	public Despesa findById(Long id) {
		Optional<Despesa> obj = repository.findById(id);
		return obj.get();
	}

	//insere despesa no banco de dados
	@Transactional
	public Despesa inserirDespesa(Despesa obj) {
		return repository.save(obj);
	}
	
	//atualiza status da despesa no banco de dados
	@Transactional
	public Despesa atualizarStatusDespesa(Long id, Despesa obj) {
		try {
			Despesa entity = repository.getReferenceById(id);
			atualizarStatusDespesa(entity, obj);
			return repository.save(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}
	
	//atualiza dados da despesa no banco de dados
	@Transactional
	public Despesa atualizarDespesa(Long id, Despesa obj) {
		try {
			Despesa entity = repository.getReferenceById(id);
			atualizarDadosDespesa(entity, obj);
			return repository.save(entity);
		}catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}
	
	@Transactional
	private void atualizarDadosDespesa(Despesa entity, Despesa obj) {
		entity.setNome(obj.getNome());
		entity.setCategoria(obj.getCategoria());
		entity.setValor(obj.getValor());
		entity.setObservacao(obj.getObservacao());
		entity.setDataDespesa(obj.getDataDespesa());
	}
	
	@Transactional
	private void atualizarStatusDespesa(Despesa entity, Despesa obj) {
		entity.setStatus(obj.getStatus());
	}
}
