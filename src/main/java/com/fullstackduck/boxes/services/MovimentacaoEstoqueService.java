package com.fullstackduck.boxes.services;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.fullstackduck.boxes.entities.MovimentacaoEstoque;
import com.fullstackduck.boxes.entities.Produto;
import com.fullstackduck.boxes.repositories.MovimentacaoEstoqueRepository;
import com.fullstackduck.boxes.repositories.ProdutoRepository;

@Service
public class MovimentacaoEstoqueService {

    @Autowired
    private MovimentacaoEstoqueRepository repository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Async
    public CompletableFuture<List<MovimentacaoEstoque>> findAll() {
        return CompletableFuture.completedFuture(repository.findAll());
    }

    @Async
    public CompletableFuture<MovimentacaoEstoque> findById(Long id) {
        return repository.findById(id).map(CompletableFuture::completedFuture)
                .orElse(CompletableFuture.completedFuture(null));
    }

    @Async
    public CompletableFuture<MovimentacaoEstoque> inserirMovimentacaoEstoque(MovimentacaoEstoque obj) {
        return CompletableFuture.completedFuture(repository.save(obj));
    }

    @Async
    public CompletableFuture<Void> adicionarItem(Long produto_id, Integer quantidade) {
        Optional<Produto> optionalProduto = produtoRepository.findById(produto_id);

        if (optionalProduto.isPresent()) {
            Produto produto = optionalProduto.get();
            int quantidadeExistente = produto.getQuantidade();
            int novaQuantidade = quantidadeExistente + quantidade;
            produto.setQuantidade(novaQuantidade);

            MovimentacaoEstoque movimentacaoEstoque = new MovimentacaoEstoque();
            movimentacaoEstoque.setProduto(produto);
            movimentacaoEstoque.setQuantidade(quantidade);
            movimentacaoEstoque.setDataMovimentacao(Instant.now());

            produtoRepository.save(produto);
            return repository.save(movimentacaoEstoque).thenApply(null);
        } else {
            throw new IllegalArgumentException("Produto não encontrado com ID: " + produto_id);
        }
    }

    @Async
    public CompletableFuture<Void> removerItem(Long produto_id, Integer quantidade) {
        Optional<Produto> optionalProduto = produtoRepository.findById(produto_id);

        if (optionalProduto.isPresent()) {
            Produto produto = optionalProduto.get();
            int quantidadeExistente = produto.getQuantidade();
            int novaQuantidade = quantidadeExistente - quantidade;
            produto.setQuantidade(novaQuantidade);

            MovimentacaoEstoque movimentacaoEstoque = new MovimentacaoEstoque();
            movimentacaoEstoque.setProduto(produto);
            movimentacaoEstoque.setQuantidade(quantidade);
            movimentacaoEstoque.setDataMovimentacao(Instant.now());

            produtoRepository.save(produto);
            return repository.save(movimentacaoEstoque).thenApply(null);
        } else {
            throw new IllegalArgumentException("Produto não encontrado com ID: " + produto_id);
        }
    }
}
