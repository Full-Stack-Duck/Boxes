package com.fullstackduck.boxes.services;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fullstackduck.boxes.entities.MovimentacaoEstoque;
import com.fullstackduck.boxes.entities.Produto;
import com.fullstackduck.boxes.repositories.MovimentacaoEstoqueRepository;
import com.fullstackduck.boxes.repositories.ProdutoRepository;

@Service //Registro de componente
public class MovimentacaoEstoqueService {

	@Autowired
	private MovimentacaoEstoqueRepository repository;
	
	@Autowired
    private ProdutoRepository produtoRepository;
	
	
	
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
	
	public void adicionarItem(Long produto_id, Integer quantidade) {
	    // Recupera o produto pelo ID
	    Optional<Produto> optionalProduto = produtoRepository.findById(produto_id);

	    if (optionalProduto.isPresent()) {
	        Produto produto = optionalProduto.get();

	        // Atualiza a quantidade do produto
	        int quantidadeExistente = produto.getQuantidade();
	        int novaQuantidade = quantidadeExistente + quantidade;
	        produto.setQuantidade(novaQuantidade);

	        // Cria uma nova movimentação de estoque
	        MovimentacaoEstoque movimentacaoEstoque = new MovimentacaoEstoque();
	        movimentacaoEstoque.setProduto(produto);
	        movimentacaoEstoque.setQuantidade(quantidade);
	        movimentacaoEstoque.setDataMovimentacao(Instant.now());
	        // Defina outras propriedades da movimentação de estoque, se necessário

	        // Salva as alterações no produto e cria a nova movimentação de estoque
	        produtoRepository.save(produto);
	        repository.save(movimentacaoEstoque);
	    } else {
	        // Lidar com o caso em que o produto não foi encontrado
	        throw new IllegalArgumentException("Produto não encontrado com ID: " + produto_id);
	    }
	}
	
	public void removerItem(Long produto_id,Integer quantidade) {
		
		 // Recupera o produto pelo ID
	    Optional<Produto> optionalProduto = produtoRepository.findById(produto_id);

	    if (optionalProduto.isPresent()) {
	        Produto produto = optionalProduto.get();

	        // Atualiza a quantidade do produto
	        int quantidadeExistente = produto.getQuantidade();
	        int novaQuantidade = quantidadeExistente - quantidade;
	        produto.setQuantidade(novaQuantidade);

	        // Cria uma nova movimentação de estoque
	        MovimentacaoEstoque movimentacaoEstoque = new MovimentacaoEstoque();
	        movimentacaoEstoque.setProduto(produto);
	        movimentacaoEstoque.setQuantidade(quantidade);
	        movimentacaoEstoque.setDataMovimentacao(Instant.now());
	        // Defina outras propriedades da movimentação de estoque, se necessário

	        // Salva as alterações no produto e cria a nova movimentação de estoque
	        produtoRepository.save(produto);
	        repository.save(movimentacaoEstoque);
	    } else {
	        // Lidar com o caso em que o produto não foi encontrado
	        throw new IllegalArgumentException("Produto não encontrado com ID: " + produto_id);
	    }
  	}


}
