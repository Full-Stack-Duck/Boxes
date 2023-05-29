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

@Service //Registro de componente
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
	
	public List<Orcamento> findAll(){
		List<Orcamento> orcamentos = orcamentoRepository.findAll();
		for (Orcamento i: orcamentos) {
		    calcularTotal(i);
		}
		return orcamentos;
	}
	
	public Orcamento findById(Long id) {
		Optional<Orcamento> obj = orcamentoRepository.findById(id);
		Orcamento orcamento = obj.get();
	    calcularTotal(orcamento);
	    return orcamento;
	}

	//insere orcamento no banco de dados
	public Orcamento inserirOrcamento(Orcamento obj) {
		return orcamentoRepository.save(obj);
	}
	
	//atualiza status do orcamento no banco de dados
	public Orcamento atualizarStatusOrcamento(Long id, Orcamento obj) {
		try {
			Orcamento entity = orcamentoRepository.getReferenceById(id);
			atualizarStatus(entity, obj);
			return orcamentoRepository.save(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}
	
	//atualiza dados do orcamento no banco de dados
	public Orcamento atualizarOrcamento(Long id, Orcamento obj) {
		try {
			Orcamento entity = orcamentoRepository.getReferenceById(id);
			atualizarDados(entity, obj);
			return orcamentoRepository.save(entity);
		}catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}
	
	public Orcamento adicionarItem(Long orcamentoId, Integer produtoId, ItensOrcamento item) {
	    Orcamento orcamento = orcamentoRepository.getReferenceById(orcamentoId);
	    Produto produto = produtoRepository.getReferenceById(produtoId);
	    item.setProduto(produto);
	    item.setPrecoUnit(produto.getValor());
	    item.setOrcamento(orcamento);
	    item.setPrecoTotal(produto.getValor());
	    orcamento.adicionarItem(item);
	    itensRepository.save(item);
	    return orcamentoRepository.save(orcamento);
	  }
	
	public Orcamento removerItem(Long orcamentoId, Long produtoId) {
	    Orcamento orcamento = orcamentoRepository.findById(orcamentoId).orElseThrow(() -> new ResourceNotFoundException("Orcamento não encontrado com o id: " + orcamentoId));
	    Produto produto = produtoRepository.findById(produtoId).orElseThrow(() -> new ResourceNotFoundException("Produto não encontrado com o id: " + produtoId));
	    ItensOrcamentoPK id = new ItensOrcamentoPK(orcamento, produto);
	    ItensOrcamento item = itensRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("ItensOrcamento não encontrado para o orcamento com id: " + orcamentoId + " e produto com id: " + produtoId));
	    orcamento.getItens().remove(item);
	    return orcamentoRepository.save(orcamento);
	}
	
	@Transactional
	public List<Orcamento> listarOrcamentos(Long idUsuario) {
        Usuario usuario = usuarioRepository.getReferenceById(idUsuario);
        return usuario.getOrcamentos();
    }
	
	@Transactional
	public List<Orcamento> listarOrcamentoPeriodo(String dataInicio, String dataFim) {
		DateTimeFormatter formatter = DateTimeFormatter.ISO_INSTANT;
		Instant data1 = Instant.from(formatter.parse(dataInicio));
		Instant data2 = Instant.from(formatter.parse(dataFim));
	    return orcamentoRepository.findByDataOrcamentoBetween(data1, data2);
	}

	
	private void atualizarDados(Orcamento entity, Orcamento obj) {
		entity.setTipoEntrega(obj.getTipoEntrega());
	}
	
	private void atualizarStatus(Orcamento entity, Orcamento obj) {
		entity.setStatus(obj.getStatus());
	}
    
    public void calcularTotal(Orcamento obj) {
    	Double total = 0.0;
		for(ItensOrcamento i: obj.getItens()) {
			total = total + i.getPrecoTotal();
		}
		total -= obj.getDesconto();
		obj.setTotal(total);
		orcamentoRepository.save(obj);
	}
    
    private void validarItemEstoque(Orcamento orcamento) throws EstoqueInsuficienteException{
        for (ItensOrcamento item : orcamento.getItens()) {
        	Produto produto = item.getProduto();
        	if(produto.getCategoria() == TipoArmazenamento.ESTOCAVEL){
	            int quantidadeSolicitada = item.getQuantidade();
	            int quantidadeDisponivel = produto.getQuantidade();
	            if (quantidadeSolicitada > quantidadeDisponivel) {
	                throw new EstoqueInsuficienteException("Estoque insuficiente para o produto " + produto.getNome());
	            }
        	}
        }
    }
    
    public Pedido gerarPedido(Orcamento obj) throws EstoqueInsuficienteException {
    	/*Orcamento orcamento = orcamentoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Orcamento não encontrado com o id: " + id));*/
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
    	return pedidoRepository.save(pedido);
    }
}
