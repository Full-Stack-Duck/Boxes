package com.fullstackduck.boxes.resources;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fullstackduck.boxes.entities.Pedido;
import com.fullstackduck.boxes.services.PedidoService;

@RestController
@CrossOrigin(origins = "*")
@EnableAsync
@RequestMapping(value = "/pedidos")
public class PedidoResource {

    @Autowired
    private PedidoService service;

    @GetMapping
    public CompletableFuture<ResponseEntity<List<Pedido>>> findAll() {
        return service.findAll().thenApply(ResponseEntity::ok);
    }

    @GetMapping(value = "/{id}")
    public CompletableFuture<ResponseEntity<Pedido>> findById(@PathVariable Long id) {
        return service.findById(id).thenApply(ResponseEntity::ok);
    }

    @GetMapping(value = "/{id}/pedidos")
    public CompletableFuture<ResponseEntity<List<Pedido>>> listarPedidos(@PathVariable Long id) {
        return service.listarPedidos(id).thenApply(ResponseEntity::ok);
    }

    @GetMapping(value = "/{id}/orcamentospd")
    public CompletableFuture<List<Pedido>> listarOrcamentosPeriodo(@PathVariable Long id, @RequestParam String dataInicio, @RequestParam String dataFim) {
        return service.listarPedidosPeriodo(dataInicio, dataFim);
    }

    @PutMapping(value = "/{id}/status-pedido")
    public CompletableFuture<ResponseEntity<Pedido>> atualizarStatusPedido(@PathVariable Long id, @RequestBody Pedido obj) {
        return service.atualizarStatusPedido(id, obj).thenApply(ResponseEntity::ok);
    }

    @PutMapping(value = "/{id}/status-pagamento")
    public CompletableFuture<ResponseEntity<Pedido>> atualizarStatusPagamentoPedido(@PathVariable Long id, @RequestBody Pedido obj) {
        return service.atualizarStatusPagamentoPedido(id, obj).thenApply(ResponseEntity::ok);
    }

    @PutMapping(value = "/{id}/cancelarPedido")
    public CompletableFuture<ResponseEntity<String>> cancelarPedido(@PathVariable Long id) throws JsonProcessingException {
        return CompletableFuture.supplyAsync(() -> {
            try {
                service.cancelarPedido(id);
                String successMessage = "Pedido cancelado";
                Map<String, String> doneMap = new HashMap<>();
                doneMap.put("Cancelado", successMessage);

                ObjectMapper objectMapper = new ObjectMapper();
                String json = objectMapper.writeValueAsString(doneMap);
                return ResponseEntity.status(HttpStatus.CREATED).body(json);
            } catch (JsonProcessingException e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao processar a resposta JSON");
            }
        });
    }

}
