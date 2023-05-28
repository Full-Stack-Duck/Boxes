package com.fullstackduck.boxes.services;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fullstackduck.boxes.entities.Produto;
import com.fullstackduck.boxes.entities.Usuario;
import com.fullstackduck.boxes.entities.enums.TipoArmazenamento;
import com.fullstackduck.boxes.entities.enums.TipoProduto;
import com.fullstackduck.boxes.repositories.ProdutoRepository;
import com.fullstackduck.boxes.repositories.UsuarioRepository;
import com.fullstackduck.boxes.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Async
    public CompletableFuture<List<Produto>> findAll() {
        return CompletableFuture.completedFuture(produtoRepository.findAll());
    }

    @Async
    public CompletableFuture<Produto> findById(Long id) {
        Optional<Produto> obj = produtoRepository.findById(id);
        return CompletableFuture.completedFuture(obj.orElseThrow(() -> new ResourceNotFoundException(id)));
    }

    @Transactional
    @Async
    public CompletableFuture<Produto> inserirProduto(Produto obj) {
        return CompletableFuture.completedFuture(produtoRepository.save(obj));
    }

    @Transactional
    @Async
    public CompletableFuture<Produto> atualizarStatusProduto(Long id, Produto obj) {
        try {
            Produto entity = produtoRepository.getReferenceById(id);
            atualizarStatus(entity, obj);
            Produto produto = produtoRepository.save(entity);
            return CompletableFuture.completedFuture(produto);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(id);
        }
    }

    @Transactional
    @Async
    public CompletableFuture<Produto> atualizarProduto(Long id, Produto obj) {
        try {
            Produto entity = produtoRepository.getReferenceById(id);
            atualizarDados(entity, obj);
            Produto produto = produtoRepository.save(entity);
            return CompletableFuture.completedFuture(produto);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(id);
        }
    }

    @Async
    private void atualizarDados(Produto entity, Produto obj) {
        entity.setNome(obj.getNome());
        entity.setValor(obj.getValor());
        entity.setCategoria(obj.getCategoria());
        entity.setQuantidade(obj.getQuantidade());
        entity.setTipo(obj.getTipo());
        entity.setObservacao(obj.getObservacao());
    }

    @Transactional
    @Async
    public CompletableFuture<List<Produto>> listarProdutos(Long idUsuario) {
        Usuario usuario = usuarioRepository.getReferenceById(idUsuario);
        return CompletableFuture.completedFuture(usuario.getProdutos());
    }

    @Async
    private void atualizarStatus(Produto entity, Produto obj) {
        entity.setStatus(obj.getStatus());
    }

    @Async
    public CompletableFuture<List<Produto>> listarProdutosCategoria(TipoArmazenamento categoria) {
        return CompletableFuture.completedFuture(produtoRepository.findByCategoria(categoria));
    }

    @Async
    public CompletableFuture<List<Produto>> listarProdutosTipo(TipoProduto tipo) {
        return CompletableFuture.completedFuture(produtoRepository.findByTipo(tipo));
    }
}
