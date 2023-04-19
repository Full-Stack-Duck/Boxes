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
	
	//insere movimentação de estoque no banco de dados
	
	public MovimentacaoEstoque inserirMovimentacaoEstoque(MovimentacaoEstoque obj) {
		return repository.save(obj);
	}
	
	/*
	public void adicionarItemEstoque(Long idProduto, Integer quantidade, Long idEstoque) {
	    Produto produto = findById(idProduto);
	    Estoque estoque = EstoqueService.findById(idEstoque);

	    // Cria uma nova movimentação no estoque
	    MovimentacaoEstoque movimentacao = new MovimentacaoEstoque();
	    movimentacao.setProduto(produto);
	    movimentacao.setEstoque(estoque);
	    movimentacao.setQuantidade(quantidade);
	    movimentacao.setDataMovimentacao(Instant.now());

	    // Atualiza a quantidade de produtos no estoque
	    estoque.setQuantidade(estoque.getQuantidade() + quantidade);
	    EstoqueService.atualizarEstoque(idEstoque, estoque);

	    // Salva a movimentação no estoque
	    repository.save(movimentacao);
	}
	
	public void removerItemEstoque(Long idProduto, Integer quantidade, Long idEstoque) {
	    Produto produto = findById(idProduto);
	    Estoque estoque = EstoqueService.findById(idEstoque);

	    // Verifica se a quantidade a ser removida é maior do que a quantidade em estoque
	    if (quantidade > estoque.getQuantidade()) {
	        throw new RuntimeException("Quantidade a ser removida é maior do que a quantidade em estoque");
	    }

	    // Cria uma nova movimentação no estoque
	    MovimentacaoEstoque movimentacao = new MovimentacaoEstoque();
	    movimentacao.setProduto(produto);
	    movimentacao.setEstoque(estoque);
	    movimentacao.setQuantidade(-quantidade); // quantidade negativa indica remoção
	    movimentacao.setDataMovimentacao(Instant.now());

	    // Atualiza a quantidade de produtos no estoque
	    estoque.setQuantidade(estoque.getQuantidade() - quantidade);
	    EstoqueService.atualizarEstoque(idEstoque, estoque);

	    // Salva a movimentação no estoque
	    movimentacaoRepository.save(movimentacao);
	}
	
	public void atualizarEstoque(Produto produto, Integer quantidadeMovimentada) {
        Estoque estoque = EstoqueRepository.findByProduto(produto);

        if (estoque == null) {
            // cria um novo estoque se não existir para esse produto
            estoque = new Estoque();
            estoque.setProduto(produto);
            estoque.setQuantidade(0);
        }

        // atualiza a quantidade de acordo com a movimentação (entrada ou saída)
        if (quantidadeMovimentada > 0) {
            estoque.setQuantidade(estoque.getQuantidade() + quantidadeMovimentada);
        } else {
            int novaQuantidade = estoque.getQuantidade() + quantidadeMovimentada;
            if (novaQuantidade < 0) {
                throw new IllegalArgumentException("Não é possível remover mais itens do estoque do que o estoque atualmente possui.");
            }
            estoque.setQuantidade(novaQuantidade);
        }

        estoqueRepository.save(estoque);
    } */


}
