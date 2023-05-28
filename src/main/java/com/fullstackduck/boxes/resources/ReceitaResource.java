package com.fullstackduck.boxes.resources;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fullstackduck.boxes.entities.Receita;
import com.fullstackduck.boxes.services.ReceitaService;

@RestController
@CrossOrigin(origins = "*")
@EnableAsync
@RequestMapping(value = "/receitas")
public class ReceitaResource {

    @Autowired
    private ReceitaService service;

    @GetMapping
    public CompletableFuture<ResponseEntity<List<Receita>>> findAll() {
        return service.findAll().thenApply(ResponseEntity::ok);
    }

    @GetMapping(value = "/{id}")
    public CompletableFuture<ResponseEntity<Receita>> findById(@PathVariable Long id) {
        return service.findById(id).thenApply(ResponseEntity::ok);
    }

    @GetMapping("/total")
    public CompletableFuture<ResponseEntity<Double>> getTotalReceita() {
        return service.getTotalReceita().thenApply(ResponseEntity::ok);
    }

    @GetMapping(value = "/{id}/Receita")
    public CompletableFuture<ResponseEntity<List<Receita>>> listarReceita(@PathVariable Long id) {
        return service.listarReceitas(id).thenApply(ResponseEntity::ok);
    }



    @GetMapping(value = "/{id}/Receitapd")
    public CompletableFuture<List<Receita>> listarReceitaPeriodo(@PathVariable Long id,
            @RequestParam String dataInicio, @RequestParam String dataFim) {
        return service.listarReceitasPeriodo(dataInicio, dataFim);
    }
}
