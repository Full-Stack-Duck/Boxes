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

import com.fullstackduck.boxes.entities.Cliente;
import com.fullstackduck.boxes.services.ClienteService;

import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "*")
@EnableAsync
@RequestMapping(value = "/clientes")
public class ClienteResource {

	@Autowired
	private ClienteService service;
	
	
	@GetMapping
	public CompletableFuture<ResponseEntity<List<Cliente>>> findAll(){
		return service.findAll().thenApply(ResponseEntity::ok);
	}
	
	
	@GetMapping(value = "/{id}")
	public CompletableFuture<ResponseEntity<Cliente>> findById(@PathVariable Long id){
		return service.findById(id).thenApply(ResponseEntity::ok);
	}

	
	@PostMapping
	@Transactional
	public CompletableFuture<ResponseEntity<Cliente>> inserirCliente(@Valid @RequestBody Cliente obj) {
		return service.inserirCliente(obj).thenApply(cliente -> {
			URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(cliente.getId()).toUri();
			return ResponseEntity.created(uri).body(cliente);
		});
	}
	

	@PutMapping(value = "/{id}/attStatusCliente")
	@Transactional
	public CompletableFuture<ResponseEntity<Cliente>> atualizarStatusCliente(@PathVariable Long id, @RequestBody Cliente obj){
		return service.atualizarStatusCliente(id, obj).thenApply(ResponseEntity::ok);
	}
	
	
	@PutMapping(value = "/{id}/attCliente")
	@Transactional
	public CompletableFuture<ResponseEntity<Cliente>> atualizarCliente(@PathVariable Long id, @RequestBody Cliente obj){
		return service.atualizarCliente(id, obj).thenApply(ResponseEntity::ok);
	}
	
	@GetMapping(value = "/{id}/orcamentos")
	public CompletableFuture<ResponseEntity<List<Cliente>>> listarClientes(@PathVariable Long id) {
	    return service.listarClientes(id).thenApply(ResponseEntity::ok);
	}
	
	@GetMapping(value = "/{id}/orcamentospd")
	public CompletableFuture<List<Cliente>> listarClientesPeriodo(@PathVariable Long id,@RequestParam String dataInicio, @RequestParam String dataFim){
		return service.listarClientePeriodo(dataInicio, dataFim);
	}
}
