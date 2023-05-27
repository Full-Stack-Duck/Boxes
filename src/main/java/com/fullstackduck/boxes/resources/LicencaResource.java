package com.fullstackduck.boxes.resources;

import java.net.URI;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fullstackduck.boxes.entities.Licenca;
import com.fullstackduck.boxes.services.LicencaService;

import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "*")
@EnableAsync
@RequestMapping(value = "/licencas")
public class LicencaResource {

    @Autowired
    private LicencaService service;

    @GetMapping
    public CompletableFuture<ResponseEntity<List<Licenca>>> findAll() {
        return service.findAll().thenApply(ResponseEntity::ok);
    }

    @GetMapping(value = "/{id}")
    public CompletableFuture<ResponseEntity<Licenca>> findById(@PathVariable Long id) {
        return service.findById(id).thenApply(ResponseEntity::ok);
    }

    @PostMapping(value = "/inserirLicenca/{usuarioId}")
    public CompletableFuture<ResponseEntity<Licenca>> inserirLicenca(@Valid @RequestBody Licenca obj,
            @PathVariable Integer usuarioId) {
        return service.inserirLicenca(obj, usuarioId).thenApply(l -> {
            URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(l.getId()).toUri();
            return ResponseEntity.created(uri).body(l);
        });
    }

    @PutMapping(value = "/{id}/renovarLicenca")
    public CompletableFuture<ResponseEntity<Licenca>> renovarLicenca(@PathVariable Long id, @RequestBody Licenca obj) {
        return service.renovarLicenca(id, obj).thenApply(ResponseEntity::ok);
    }

    @PostMapping(value = "/alterarLicenca/{usuarioId}")
    public CompletableFuture<ResponseEntity<Licenca>> alterarLicenca(@Valid @RequestBody Licenca obj,
            @PathVariable Integer usuarioId) {
        return service.alterarLicenca(obj, usuarioId).thenApply(l -> {
            URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(l.getId()).toUri();
            return ResponseEntity.created(uri).body(l);
        });
    }
}
