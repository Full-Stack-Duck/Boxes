package com.fullstackduck.boxes.services;

import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fullstackduck.boxes.entities.ItensOrcamento;
import com.fullstackduck.boxes.entities.Orcamento;
import com.fullstackduck.boxes.entities.Pedido;
import com.fullstackduck.boxes.entities.Produto;
import com.fullstackduck.boxes.entities.Usuario;
import com.fullstackduck.boxes.entities.enums.Status;
import com.fullstackduck.boxes.entities.enums.StatusPedido;
import com.fullstackduck.boxes.entities.enums.TipoArmazenamento;
import com.fullstackduck.boxes.repositories.OrcamentoRepository;
import com.fullstackduck.boxes.repositories.PedidoRepository;
import com.fullstackduck.boxes.repositories.UsuarioRepository;
import com.fullstackduck.boxes.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service // Registro de componente
public class PedidoService {

	@Autowired
	private PedidoRepository pedidoRepository;

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private OrcamentoRepository orcamentoRepository;

	public List<Pedido> findAll() {
		return pedidoRepository.findAll();
	}

	public Pedido findById(Long id) {
		Optional<Pedido> obj = pedidoRepository.findById(id);
		return obj.get();
	}

	// insere cliente no banco de dados

	public Pedido inserirPedido(Pedido obj) {
		return pedidoRepository.save(obj);
	}

	@Transactional
	public List<Pedido> listarPedidos(Long idUsuario) {
		Usuario usuario = usuarioRepository.getReferenceById(idUsuario);
		return usuario.getPedidos();
	}

	@Transactional
	public List<Pedido> listarPedidosPeriodo(String dataInicio, String dataFim) {
		DateTimeFormatter formatter = DateTimeFormatter.ISO_INSTANT;
		Instant data1 = Instant.from(formatter.parse(dataInicio));
		Instant data2 = Instant.from(formatter.parse(dataFim));
		return pedidoRepository.findByDataPedidoBetween(data1, data2);
	}

	@Transactional
	public Pedido atualizarStatusPagamentoPedido(Long id, Pedido obj) {
		try {
			Pedido entity = pedidoRepository.getReferenceById(id);
			atualizarDadosPagamentoPedido(entity, obj);
			return pedidoRepository.save(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}

	@Transactional
	private void atualizarDadosPagamentoPedido(Pedido entity, Pedido obj) {
		entity.setStatusPagamentoPedido(obj.getStatusPagamentoPedido());

	}

	// atualiza dados do cliente no banco de dados
	@Transactional
	public Pedido atualizarStatusPedido(Long id, Pedido obj) {
		try {
			Pedido entity = pedidoRepository.getReferenceById(id);
			atualizarDadosPedido(entity, obj);
			return pedidoRepository.save(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}

	private void atualizarDadosPedido(Pedido entity, Pedido obj) {
		entity.setStatusPedido(obj.getStatusPedido());
	}

	public void cancelarPedido(Long id) {
		Pedido obj = pedidoRepository.getReferenceById(id);
		Orcamento orc = obj.getOrcamento();
		for (ItensOrcamento item : orc.getItens()) {
        	Produto produto = item.getProduto();
        	if(produto.getCategoria() == TipoArmazenamento.ESTOCAVEL){
	            produto.setQuantidade(produto.getQuantidade() + item.getQuantidade());
        	}
        }
		orc.setStatus(Status.ATIVO);
		obj.setStatusPedido(StatusPedido.CANCELADO);
		orcamentoRepository.save(orc);
		pedidoRepository.save(obj);
	}
}
