package com.fullstackduck.boxes.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fullstackduck.boxes.entities.ItensOrcamento;
import com.fullstackduck.boxes.entities.Orcamento;
import com.fullstackduck.boxes.entities.Produto;
import com.fullstackduck.boxes.repositories.ItensOrcamentoRepository;
import com.fullstackduck.boxes.repositories.OrcamentoRepository;
import com.fullstackduck.boxes.repositories.ProdutoRepository;
import com.fullstackduck.boxes.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service //Registro de componente
public class OrcamentoService {
	
	 private static final double DESCONTO_PADRAO = 0.1;

	@Autowired
	private OrcamentoRepository orcamentoRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private ItensOrcamentoRepository itensRepository;
	
	@Autowired
	private ItensOrcamentoService itensService;
	
	public List<Orcamento> findAll(){
		return orcamentoRepository.findAll();
	}
	
	public Orcamento findById(Long id) {
		Optional<Orcamento> obj = orcamentoRepository.findById(id);
		return obj.get();
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
	
	
	  public Orcamento adicionarItem(Long orcamentoId, Integer produtoId, Integer quantidade) {
	    Orcamento orcamento = orcamentoRepository.getReferenceById(orcamentoId);
	    Produto produto = produtoRepository.getReferenceById(produtoId);
	    ItensOrcamento item = new ItensOrcamento();
	    item.setProduto(produto);
	    item.setPrecoUnit(produto.getValor());
	    item.setQuantidade(quantidade);
	    item.setOrcamento(orcamento);
	    orcamento.adicionarItem(item);
	    itensRepository.save(item);
	    return orcamentoRepository.save(orcamento);
	  }
	
	private void atualizarDados(Orcamento entity, Orcamento obj) {
		entity.setTipoEntrega(obj.getTipoEntrega());
	}
	
	private void atualizarStatus(Orcamento entity, Orcamento obj) {
		entity.setStatus(obj.getStatus());
	}
	
	/*public double calcularDesconto(Orcamento orcamento) {
        double valorTotal = orcamento.getTotal();
        if (valorTotal >= 500 && valorTotal < 1000) {
            return valorTotal * 0.05;
        } else if (valorTotal >= 1000) {
            return valorTotal * DESCONTO_PADRAO;
        } else {
            return 0.0;
        }
    }

    public double calcularTotal(Orcamento orcamento) {
        double valorTotal = orcamento.getTotal();
        double desconto = calcularDesconto(orcamento);
        return valorTotal - desconto;
    }*/
    
    public void calcularTotal(Long id) {
    	Orcamento orcamento = orcamentoRepository.getReferenceById(id);
		for(ItensOrcamento i: orcamento.getItens()) {
			orcamento.setTotal(orcamento.getTotal() + i.getPrecoTotal());
		}
	}
    
}
