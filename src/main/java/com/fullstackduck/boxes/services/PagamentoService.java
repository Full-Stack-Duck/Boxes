package com.fullstackduck.boxes.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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
	private PagamentoRepository pagamentoRepository;

	public List<Pagamento> findAll() {
		return pagamentoRepository.findAll();
	}

	public Pagamento findById(Long id) {
		Optional<Pagamento> obj = pagamentoRepository.findById(id);
		return obj.get();
	}

	// insere pagamento no banco de dados
	public Pagamento inserirPagamento(Pagamento obj) {
		return pagamentoRepository.save(obj);
	}

	// atualiza dados do cliente no banco de dados
	@Transactional
	public Pagamento atualizarPagamento(Long id, Pagamento obj) {
		try {
			Pagamento entity = pagamentoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Pagamento não encontrado com o id: " + id));
			atualizarDadosPagamento(entity, obj);
			return pagamentoRepository.save(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}

	@Transactional
	public Pagamento atualizarStatusPagamento(Long id, Pagamento obj) {
		try {
			Pagamento entity = pagamentoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Pagamento não encontrado com o id: " + id));
			devolverPagamento(entity, obj);
			return pagamentoRepository.save(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}

	private void atualizarDadosPagamento(Pagamento entity, Pagamento obj) {
		entity.setValor(obj.getValor());
		entity.setDataPagamento(obj.getDataPagamento());
		entity.setFormaPagamento(obj.getFormaPagamento());
		entity.setPedido(obj.getPedido());
	}

	private void devolverPagamento(Pagamento entity, Pagamento obj) {
		entity.setStatusPagamento(StatusPagamento.DEVOLVIDO);
	}
}
