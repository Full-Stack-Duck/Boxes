package com.fullstackduck.boxes.resources;

import java.net.URI;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fullstackduck.boxes.entities.Produto;
import com.fullstackduck.boxes.entities.enums.TipoArmazenamento;
import com.fullstackduck.boxes.entities.enums.TipoProduto;
import com.fullstackduck.boxes.services.ProdutoService;

import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "*")
@EnableAsync
@RequestMapping(value = "/produtos")
public class ProdutoResource {

    @Autowired
    private ProdutoService service;

    @GetMapping
    public CompletableFuture<ResponseEntity<List<Produto>>> findAll() {
        return service.findAll().thenApply(ResponseEntity::ok);
    }

    @GetMapping(value = "/{id}")
    public CompletableFuture<ResponseEntity<Produto>> findById(@PathVariable Long id) {
        return service.findById(id).thenApply(ResponseEntity::ok);
    }

    @PostMapping
    @Transactional
    public CompletableFuture<ResponseEntity<Produto>> inserirProduto(@Valid @RequestBody Produto obj) {
        return service.inserirProduto(obj).thenApply(produto -> {
            URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(produto.getId()).toUri();
            return ResponseEntity.created(uri).body(produto);
        });
    }

    @PutMapping(value = "/{id}/attStatusProduto")
    @Transactional
    public CompletableFuture<ResponseEntity<Produto>> atualizarStatusProduto(@PathVariable Long id, @RequestBody Produto obj) {
        return service.atualizarStatusProduto(id, obj).thenApply(ResponseEntity::ok);
    }

    @PutMapping(value = "/{id}/attProduto")
    @Transactional
    public CompletableFuture<ResponseEntity<Produto>> atualizarProduto(@PathVariable Long id, @RequestBody Produto obj) {
        return service.atualizarProduto(id, obj).thenApply(ResponseEntity::ok);
    }

    @GetMapping(value = "/{id}/produtos")
    public CompletableFuture<ResponseEntity<List<Produto>>> listarProdutos(@PathVariable Long id) {
        return service.listarProdutos(id).thenApply(ResponseEntity::ok);
    }

    @GetMapping(value = "/categoria")
    public CompletableFuture<CompletableFuture<List<Produto>>> listarProdutosCategoria(@RequestParam(value = "categoria") TipoArmazenamento categoria) {
        return CompletableFuture.completedFuture(service.listarProdutosCategoria(categoria));
    }

    @GetMapping(value = "/tipo")
    public CompletableFuture<CompletableFuture<List<Produto>>> listarProdutosTipo(@RequestParam(value = "tipo") TipoProduto tipo) {
        return CompletableFuture.completedFuture(service.listarProdutosTipo(tipo));
    }
}
