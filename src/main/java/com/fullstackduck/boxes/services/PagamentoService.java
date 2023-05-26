package com.fullstackduck.boxes.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fullstackduck.boxes.entities.Pagamento;
import com.fullstackduck.boxes.entities.enums.StatusPagamento;
import com.fullstackduck.boxes.repositories.PagamentoRepository;
import com.fullstackduck.boxes.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service // Registro de componente
public class PagamentoService {

	@Autowired
	private PagamentoRepository repository;

	@Async
	public List<Pagamento> findAll() {
		return repository.findAll();
	}

	@Async
	public Pagamento findById(Long id) {
		Optional<Pagamento> obj = repository.findById(id);
		return obj.get();
	}

	// insere pagamento no banco de dados
	@Async
	public Pagamento inserirPagamento(Pagamento obj) {
		return repository.save(obj);
	}

	// atualiza dados do cliente no banco de dados
	@Transactional
	@Async
	public Pagamento atualizarPagamento(Long id, Pagamento obj) {
		try {
			Pagamento entity = repository.getReferenceById(id);
			atualizarDadosPagamento(entity, obj);
			return repository.save(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}

	@Transactional
	@Async
	public Pagamento atualizarStatusPagamento(Long id, Pagamento obj) {
		try {
			Pagamento entity = repository.getReferenceById(id);
			devolverPagamento(entity, obj);
			return repository.save(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}

	@Async
	private void atualizarDadosPagamento(Pagamento entity, Pagamento obj) {
		entity.setValor(obj.getValor());
		entity.setDataPagamento(obj.getDataPagamento());
		entity.setFormaPagamento(obj.getFormaPagamento());
		entity.setPedido(obj.getPedido());
	}

	@Async
	private void devolverPagamento(Pagamento entity, Pagamento obj) {
		entity.setStatusPagamento(StatusPagamento.DEVOLVIDO);
	}
}
