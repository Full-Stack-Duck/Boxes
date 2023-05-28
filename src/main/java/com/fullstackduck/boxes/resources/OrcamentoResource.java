package com.fullstackduck.boxes.resources;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fullstackduck.boxes.entities.ItensOrcamento;
import com.fullstackduck.boxes.entities.Orcamento;
import com.fullstackduck.boxes.services.OrcamentoService;
import com.fullstackduck.boxes.services.exceptions.EstoqueInsuficienteException;

import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "*")
@EnableAsync
@RequestMapping("/orcamentos")
public class OrcamentoResource {

    @Autowired
    private OrcamentoService orcamentoService;

    @GetMapping
    public CompletableFuture<List<Orcamento>> findAll() {
        return orcamentoService.findAll();
    }

    @GetMapping("/{id}")
    public CompletableFuture<ResponseEntity<Orcamento>> findById(@PathVariable Long id) {
        return orcamentoService.findById(id)
                .thenApply(ResponseEntity::ok)
                .exceptionally(e -> ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Transactional
    public CompletableFuture<Orcamento> inserirOrcamento(@Valid @RequestBody Orcamento obj) {
        return orcamentoService.inserirOrcamento(obj);
    }

    @PutMapping("/{id}/attStatus")
    @Transactional
    public CompletableFuture<ResponseEntity<Orcamento>> atualizarStatusOrcamento(@PathVariable Long id, @RequestBody Orcamento obj) {
        return orcamentoService.atualizarStatusOrcamento(id, obj)
                .thenApply(ResponseEntity::ok)
                .exceptionally(e -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}/attOrcamento")
    @Transactional
    public CompletableFuture<ResponseEntity<Orcamento>> atualizarOrcamento(@PathVariable Long id, @RequestBody Orcamento obj) {
        return orcamentoService.atualizarOrcamento(id, obj)
                .thenApply(ResponseEntity::ok)
                .exceptionally(e -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}/adicionarItem/{produtoId}")
    @Transactional
    public CompletableFuture<ResponseEntity<Orcamento>> adicionarItem(@PathVariable Long id, @PathVariable Integer produtoId, @RequestBody ItensOrcamento item) {
        return orcamentoService.adicionarItem(id, produtoId, item)
                .thenApply(ResponseEntity::ok)
                .exceptionally(e -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @Transactional
    public CompletableFuture<ResponseEntity<Orcamento>> removerItem(@PathVariable Long id, @RequestParam Long produtoId) {
        return orcamentoService.removerItem(id, produtoId)
                .thenApply(ResponseEntity::ok)
                .exceptionally(e -> ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}/orcamentos")
    public CompletableFuture<ResponseEntity<List<Orcamento>>> listarOrcamentos(@PathVariable Long id) {
        return orcamentoService.listarOrcamentos(id)
                .thenApply(ResponseEntity::ok)
                .exceptionally(e -> ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}/orcamentospd")
    public CompletableFuture<List<Orcamento>> listarOrcamentosPeriodo(@PathVariable Long id, @RequestParam String dataInicio, @RequestParam String dataFim) {
        return orcamentoService.listarOrcamentoPeriodo(dataInicio, dataFim);
    }

    @PostMapping("/{id}/gerarPedido")
    @Transactional
    public CompletableFuture<ResponseEntity<Map<String, String>>> gerarPedido(@PathVariable Long id) {
        try {
            return orcamentoService.gerarPedido(id)
                    .thenApply(pedido -> {
                        Map<String, String> response = new HashMap<>();
                        response.put("message", "Pedido gerado com sucesso.");
                        response.put("pedidoId", pedido.getId().toString());
                        return ResponseEntity.ok(response);
                    })
                    .exceptionally(e -> ResponseEntity.badRequest().build());
        } catch (EstoqueInsuficienteException e) {
            Map<String, String> response = new HashMap<>();
            response.put("error", e.getMessage());
            return CompletableFuture.completedFuture(ResponseEntity.badRequest().body(response));
        }
    }
}
