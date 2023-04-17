package com.fullstackduck.boxes.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fullstackduck.boxes.entities.Pagamento;
import com.fullstackduck.boxes.repositories.PagamentoRepository;
import com.fullstackduck.boxes.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

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
	
	//insere pagamento no banco de dados
	
	public Pagamento inserirPagamento(Pagamento obj) {
		return repository.save(obj);
	}
	//atualiza status do cliente no banco de dados

	public Pagamento atualizarStatusPagamento(Long id, Pagamento obj) {
		try {
			Pagamento entity = repository.getReferenceById(id);
			atualizarStatusPagamento(entity, obj);
			return repository.save(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}
	
	//atualiza dados do cliente no banco de dados
	
	public Pagamento atualizarPagamento(Long id, Pagamento obj) {
		try {
			Pagamento entity = repository.getReferenceById(id);
			atualizarDadosPagamento(entity, obj);
			return repository.save(entity);
		}catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}
	
	private void atualizarDadosPagamento(Pagamento entity, Pagamento obj) {
		entity.setValor(obj.getValor());
		entity.setDataPagamento(obj.getDataPagamento());
		entity.setFormaPagamento(obj.getFormaPagamento());
		entity.setPedido(obj.getPedido());
	}
	
	
	private void atualizarStatusPagamento(Pagamento entity, Pagamento obj) {
		entity.setStatus(obj.getStatus());
	}
}
