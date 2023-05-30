package com.fullstackduck.boxes.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fullstackduck.boxes.entities.Pagamento;
import com.fullstackduck.boxes.services.PagamentoService;

import jakarta.validation.Valid;

//Controlador Rest
@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/pagamentos")
public class PagamentoResource {

	@Autowired
	private PagamentoService service;
	
	@GetMapping
	public ResponseEntity<List<Pagamento>> findAll(){
		List<Pagamento> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Pagamento> findById(@PathVariable Long id){
		Pagamento obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<Pagamento> inserirPagamento(@Valid @RequestBody Pagamento obj) {
		obj = service.inserirPagamento(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).body(obj);
	}
	
	@PutMapping(value = "/{id}/attStatusPagamento")
	@Transactional
	public ResponseEntity<Pagamento> atualizarStatusPagamento(@PathVariable Long id, @RequestBody Pagamento obj){
		obj = service.atualizarStatusPagamento(id, obj);
		return ResponseEntity.ok().body(obj);
	}
	
	
	@PutMapping(value = "/{id}/attPagamento")
	@Transactional
	public ResponseEntity<Pagamento> atualizarPagamento(@PathVariable Long id, @RequestBody Pagamento obj){
		obj = service.atualizarPagamento(id, obj);
		return ResponseEntity.ok().body(obj);
	}
}
