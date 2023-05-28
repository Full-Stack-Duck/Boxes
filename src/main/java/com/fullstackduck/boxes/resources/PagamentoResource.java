package com.fullstackduck.boxes.resources;

import com.fullstackduck.boxes.entities.Pagamento;
import com.fullstackduck.boxes.services.PagamentoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@CrossOrigin(origins = "*")
@EnableAsync
@RequestMapping(value = "/pagamentos")
public class PagamentoResource {

    @Autowired
    private PagamentoService service;

    @GetMapping
    public CompletableFuture<ResponseEntity<List<Pagamento>>> findAll() {
        return service.findAll().thenApply(ResponseEntity::ok);
    }

    @GetMapping(value = "/{id}")
    public CompletableFuture<ResponseEntity<Pagamento>> findById(@PathVariable Long id) {
        return service.findById(id).thenApply(ResponseEntity::ok);
    }

    @PostMapping
    @Transactional
    public CompletableFuture<ResponseEntity<Pagamento>> inserirPagamento(@Valid @RequestBody Pagamento obj) {
        return service.inserirPagamento(obj)
                .thenApply(savedObj -> {
                    URI uri = URI.create("/pagamentos/" + savedObj.getId());
                    return ResponseEntity.created(uri).body(savedObj);
                });
    }

    @PutMapping(value = "/{id}/attStatusPagamento")
    @Transactional
    public CompletableFuture<ResponseEntity<Pagamento>> atualizarStatusPagamento(@PathVariable Long id, @RequestBody Pagamento obj) {
        return service.atualizarStatusPagamento(id, obj).thenApply(ResponseEntity::ok);
    }

    @PutMapping(value = "/{id}/attPagamento")
    @Transactional
    public CompletableFuture<ResponseEntity<Pagamento>> atualizarPagamento(@PathVariable Long id, @RequestBody Pagamento obj) {
        return service.atualizarPagamento(id, obj).thenApply(ResponseEntity::ok);
    }
}
