package com.fullstackduck.boxes.resources;

import java.net.URI;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fullstackduck.boxes.entities.MovimentacaoEstoque;
import com.fullstackduck.boxes.services.MovimentacaoEstoqueService;

import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "*")
@EnableAsync
@RequestMapping(value = "/movimentacoes")
public class MovimentacaoEstoqueResource {

    @Autowired
    private MovimentacaoEstoqueService service;

    @GetMapping
    public CompletableFuture<ResponseEntity<List<MovimentacaoEstoque>>> findAll() {
        return service.findAll().thenApply(ResponseEntity::ok);
    }

    @GetMapping(value = "/{id}")
    public CompletableFuture<ResponseEntity<MovimentacaoEstoque>> findById(@PathVariable Long id) {
        return service.findById(id).thenApply(ResponseEntity::ok);
    }

    @PostMapping
    @Transactional
    public CompletableFuture<ResponseEntity<MovimentacaoEstoque>> inserirMovimentacaoEstoque(
            @Valid @RequestBody MovimentacaoEstoque obj) {
        return service.inserirMovimentacaoEstoque(obj).thenApply(createdObj -> {
            URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                    .buildAndExpand(createdObj.getId()).toUri();
            return ResponseEntity.created(uri).body(createdObj);
        });
    }

    @Transactional
    @PostMapping("/{produto_id}/adicionarItem")
    public CompletableFuture<ResponseEntity<String>> adicionarItem(@PathVariable Long produto_id,
            @RequestParam Integer quantidade) {
        if (quantidade != null) {
            return CompletableFuture.supplyAsync(() -> service.adicionarItem(produto_id, quantidade))
                    .thenApply(result -> ResponseEntity.ok("Item adicionado com sucesso"))
                    .exceptionally(e -> ResponseEntity.badRequest().body(e.getMessage()));
        } else {
            return CompletableFuture.completedFuture(ResponseEntity.badRequest().body("A quantidade não pode ser nula"));
        }
    }

    @Transactional
    @DeleteMapping("/{produto_id}/removerItem")
    public CompletableFuture<ResponseEntity<String>> removerItem(@PathVariable Long produto_id,
            @RequestParam Integer quantidade) {
        return CompletableFuture.supplyAsync(() -> service.removerItem(produto_id, quantidade))
                .thenApply(result -> ResponseEntity.ok("Item removido com sucesso"))
                .exceptionally(e -> ResponseEntity.badRequest().body(e.getMessage()));
    }
}
