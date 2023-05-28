package com.fullstackduck.boxes.services;

import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
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
import com.fullstackduck.boxes.entities.enums.StatusPagamentoPedido;
import com.fullstackduck.boxes.entities.enums.StatusPedido;
import com.fullstackduck.boxes.entities.enums.TipoArmazenamento;
import com.fullstackduck.boxes.entities.pk.ItensOrcamentoPK;
import com.fullstackduck.boxes.repositories.ItensOrcamentoRepository;
import com.fullstackduck.boxes.repositories.OrcamentoRepository;
import com.fullstackduck.boxes.repositories.PedidoRepository;
import com.fullstackduck.boxes.repositories.ProdutoRepository;
import com.fullstackduck.boxes.repositories.UsuarioRepository;
import com.fullstackduck.boxes.services.exceptions.EstoqueInsuficienteException;
import com.fullstackduck.boxes.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class OrcamentoService {

    @Autowired
    private OrcamentoRepository orcamentoRepository;

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private ItensOrcamentoRepository itensRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Async
    public CompletableFuture<List<Orcamento>> findAll() {
        List<Orcamento> orcamentos = orcamentoRepository.findAll();
        for (Orcamento i : orcamentos) {
            calcularTotal(i);
        }
        return CompletableFuture.completedFuture(orcamentos);
    }

    @Async
    public CompletableFuture<Orcamento> findById(Long id) {
        Optional<Orcamento> obj = orcamentoRepository.findById(id);
        Orcamento orcamento = obj.get();
        calcularTotal(orcamento);
        return CompletableFuture.completedFuture(orcamento);
    }

    @Async
    public CompletableFuture<Orcamento> inserirOrcamento(Orcamento obj) {
        return CompletableFuture.completedFuture(orcamentoRepository.save(obj));
    }

    @Async
    public CompletableFuture<Orcamento> atualizarStatusOrcamento(Long id, Orcamento obj) {
        try {
            Orcamento entity = orcamentoRepository.getReferenceById(id);
            atualizarStatus(entity, obj);
            return CompletableFuture.completedFuture(orcamentoRepository.save(entity));
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(id);
        }
    }

    @Async
    public CompletableFuture<Orcamento> atualizarOrcamento(Long id, Orcamento obj) {
        try {
            Orcamento entity = orcamentoRepository.getReferenceById(id);
            atualizarDados(entity, obj);
            return CompletableFuture.completedFuture(orcamentoRepository.save(entity));
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(id);
        }
    }

    @Async
    public CompletableFuture<Orcamento> adicionarItem(Long orcamentoId, Integer produtoId, ItensOrcamento item) {
        Orcamento orcamento = orcamentoRepository.getReferenceById(orcamentoId);
        Produto produto = produtoRepository.getReferenceById(produtoId);
        item.setProduto(produto);
        item.setPrecoUnit(produto.getValor());
        item.setOrcamento(orcamento);
        item.setPrecoTotal(produto.getValor());
        orcamento.adicionarItem(item);
        itensRepository.save(item);
        return CompletableFuture.completedFuture(orcamentoRepository.save(orcamento));
    }

    @Async
    public CompletableFuture<Orcamento> removerItem(Long orcamentoId, Long produtoId) {
        Orcamento orcamento = orcamentoRepository.findById(orcamentoId)
                .orElseThrow(() -> new ResourceNotFoundException("Orcamento não encontrado com o id: " + orcamentoId));
        Produto produto = produtoRepository.findById(produtoId)
                .orElseThrow(() -> new ResourceNotFoundException("Produto não encontrado com o id: " + produtoId));
        ItensOrcamentoPK id = new ItensOrcamentoPK(orcamento, produto);
        ItensOrcamento item = itensRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ItensOrcamento não encontrado para o orcamento com id: "
                        + orcamentoId + " e produto com id: " + produtoId));
        orcamento.getItens().remove(item);
        return CompletableFuture.completedFuture(orcamentoRepository.save(orcamento));
    }

    @Transactional
    @Async
    public CompletableFuture<List<Orcamento>> listarOrcamentos(Long idUsuario) {
        Usuario usuario = usuarioRepository.getReferenceById(idUsuario);
        return CompletableFuture.completedFuture(usuario.getOrcamentos());
    }

    @Transactional
    @Async
    public CompletableFuture<List<Orcamento>> listarOrcamentoPeriodo(String dataInicio, String dataFim) {
        DateTimeFormatter formatter = DateTimeFormatter.ISO_INSTANT;
        Instant data1 = Instant.from(formatter.parse(dataInicio));
        Instant data2 = Instant.from(formatter.parse(dataFim));
        return CompletableFuture.completedFuture(orcamentoRepository.findByDataOrcamentoBetween(data1, data2));
    }

    @Async
    private void atualizarDados(Orcamento entity, Orcamento obj) {
        entity.setTipoEntrega(obj.getTipoEntrega());
    }

    @Async
    private void atualizarStatus(Orcamento entity, Orcamento obj) {
        entity.setStatus(obj.getStatus());
    }

    @Async
    public CompletableFuture<Void> calcularTotal(Orcamento obj) {
        Double total = 0.0;
        for (ItensOrcamento i : obj.getItens()) {
            total = total + i.getPrecoTotal();
        }
        total -= obj.getDesconto();
        obj.setTotal(total);
        orcamentoRepository.save(obj);
        return CompletableFuture.completedFuture(null);
    }

    @Async
    private void validarItemEstoque(Orcamento orcamento) throws EstoqueInsuficienteException {
        for (ItensOrcamento item : orcamento.getItens()) {
            Produto produto = item.getProduto();
            if (produto.getCategoria() == TipoArmazenamento.ESTOCAVEL) {
                int quantidadeSolicitada = item.getQuantidade();
                int quantidadeDisponivel = produto.getQuantidade();
                if (quantidadeSolicitada > quantidadeDisponivel) {
                    throw new EstoqueInsuficienteException("Estoque insuficiente para o produto " + produto.getNome());
                }
            }
        }
    }

    @Async
    public CompletableFuture<Pedido> gerarPedido(Long id) throws EstoqueInsuficienteException {
    	Orcamento obj = orcamentoRepository.getReferenceById(id);
        validarItemEstoque(obj);
        Pedido pedido = new Pedido();
        pedido.setId(obj.getId());
        pedido.setTotal(obj.getTotal());
        pedido.setTipoEntrega(obj.getTipoEntrega());
        pedido.setDataPedido(Instant.now());
        pedido.setStatus(Status.ATIVO);
        pedido.setStatusPedido(StatusPedido.EM_FILA_PREPARACAO);
        pedido.setStatusPagamentoPedido(StatusPagamentoPedido.AGUARDANDO_PAGAMENTO);
        pedido.setUsuario(obj.getUsuario());
        pedido.setCliente(obj.getCliente());
        pedido.setOrcamento(obj);
        return CompletableFuture.completedFuture(pedidoRepository.save(pedido));
    }
}
