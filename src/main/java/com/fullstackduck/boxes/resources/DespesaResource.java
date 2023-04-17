package com.fullstackduck.boxes.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fullstackduck.boxes.entities.Despesa;
import com.fullstackduck.boxes.services.DespesaService;

//Controlador Rest
@RestController
@RequestMapping(value = "/despesas")
public class DespesaResource {

	@Autowired
	private DespesaService service;
	
	
	@GetMapping
	public ResponseEntity<List<Despesa>> findAll(){
		List<Despesa> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Despesa> findById(@PathVariable Long id){
		Despesa obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<Despesa> inserirDespesa(@RequestBody Despesa obj) {
		obj = service.inserirDespesa(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).body(obj);
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<Despesa> excluirDespesa(@PathVariable Long id) {
	    service.excluirDespesa(id);
	    return ResponseEntity.noContent().build();
	}
	
	@PutMapping(value = "/{id}/attDespesa")
	@Transactional
	public ResponseEntity<Despesa> atualizarDespesa(@PathVariable Long id, @RequestBody Despesa obj){
		obj = service.atualizarDespesa(id, obj);
		return ResponseEntity.ok().body(obj);
	}
}
