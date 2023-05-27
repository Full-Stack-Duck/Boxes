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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fullstackduck.boxes.entities.Despesa;
import com.fullstackduck.boxes.services.DespesaService;

import jakarta.validation.Valid;

// Controlador Rest
@RestController
@CrossOrigin(origins = "*")
@EnableAsync
@RequestMapping(value = "/despesas")
public class DespesaResource {

	@Autowired
	private DespesaService service;

	@GetMapping
	public CompletableFuture<ResponseEntity<List<Despesa>>> findAll() {
		return service.findAll().thenApply(ResponseEntity::ok);
	}

	@GetMapping(value = "/{id}")
	public CompletableFuture<ResponseEntity<Despesa>> findById(@PathVariable Long id) {
		return service.findById(id).thenApply(ResponseEntity::ok);
	}

	@PostMapping
	@Transactional
	public CompletableFuture<ResponseEntity<Despesa>> inserirDespesa(@Valid @RequestBody Despesa obj) {
		return service.inserirDespesa(obj).thenApply((despesa) -> {
			URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
					.buildAndExpand(despesa.getId()).toUri();
			return ResponseEntity.created(uri).body(despesa);
		});
	}

	@DeleteMapping("/{id}")
	@Transactional
	public CompletableFuture<ResponseEntity<Void>> excluirDespesa(@PathVariable Long id) {
		return service.excluirDespesa(id).thenApply((Void) -> ResponseEntity.noContent().build());
	}

	@PutMapping(value = "/{id}/attDespesa")
	@Transactional
	public CompletableFuture<ResponseEntity<Despesa>> atualizarDespesa(@PathVariable Long id,
			@RequestBody Despesa obj) {
		return service.atualizarDespesa(id, obj).thenApply(ResponseEntity::ok);
	}

	@GetMapping(value = "/{id}/despesas")
	public CompletableFuture<ResponseEntity<List<Despesa>>> listarDespesas(@PathVariable Long id) {
		return service.listarDespesas(id).thenApply(ResponseEntity::ok);
	}

	@GetMapping(value = "/{id}/despesaspd")
	public CompletableFuture<List<Despesa>> listarDespesasPeriodo(@PathVariable Long id,
			@RequestParam String dataInicio, @RequestParam String dataFim) {
		return service.listarDespesaPeriodo(dataInicio, dataFim);
	}
}
