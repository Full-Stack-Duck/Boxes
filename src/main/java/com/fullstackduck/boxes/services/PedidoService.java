package com.fullstackduck.boxes.services;

import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
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

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private OrcamentoRepository orcamentoRepository;

    @Async
    public CompletableFuture<List<Pedido>> findAll() {
        return CompletableFuture.completedFuture(pedidoRepository.findAll());
    }

    @Async
    public CompletableFuture<Pedido> findById(Long id) {
        return pedidoRepository.findById(id)
                .map(CompletableFuture::completedFuture)
                .orElseThrow(() -> new ResourceNotFoundException(id));
    }

    @Transactional
    @Async
    public CompletableFuture<Pedido> inserirPedido(Pedido obj) {
        return CompletableFuture.completedFuture(pedidoRepository.save(obj));
    }

    @Transactional
    @Async
    public CompletableFuture<List<Pedido>> listarPedidos(Long idUsuario) {
        Usuario usuario = usuarioRepository.getReferenceById(idUsuario);
        return CompletableFuture.completedFuture(usuario.getPedidos());
    }

    @Transactional
    @Async
    public CompletableFuture<List<Pedido>> listarPedidosPeriodo(String dataInicio, String dataFim) {
        DateTimeFormatter formatter = DateTimeFormatter.ISO_INSTANT;
        Instant data1 = Instant.from(formatter.parse(dataInicio));
        Instant data2 = Instant.from(formatter.parse(dataFim));
        return CompletableFuture.completedFuture(pedidoRepository.findByDataPedidoBetween(data1, data2));
    }

    @Transactional
    @Async
    public CompletableFuture<Pedido> atualizarStatusPagamentoPedido(Long id, Pedido obj) {
    	try {
			Pedido entity = pedidoRepository.getReferenceById(id);
			atualizarDadosPedido(entity, obj);
			Pedido pedido = pedidoRepository.save(entity);
			return CompletableFuture.completedFuture(pedido);
		}catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
    }

    @Async
    private void atualizarDadosPagamentoPedido(Pedido entity, Pedido obj) {
        entity.setStatusPagamentoPedido(obj.getStatusPagamentoPedido());
    }

    @Transactional
    @Async
    public CompletableFuture<Pedido> atualizarStatusPedido(Long id, Pedido obj) {
    	try {
			Pedido entity = pedidoRepository.getReferenceById(id);
			atualizarDadosPedido(entity, obj);
			Pedido pedido = pedidoRepository.save(entity);
			return CompletableFuture.completedFuture(pedido);
		}catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
    }

    @Async
    private void atualizarDadosPedido(Pedido entity, Pedido obj) {
        entity.setStatusPedido(obj.getStatusPedido());
    }

    @Async
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
