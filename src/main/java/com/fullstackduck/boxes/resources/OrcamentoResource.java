package com.fullstackduck.boxes.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fullstackduck.boxes.entities.Orcamento;
import com.fullstackduck.boxes.services.OrcamentoService;

//Controlador Rest
@RestController
@RequestMapping(value = "/orcamentos")
public class OrcamentoResource {

	@Autowired
	private OrcamentoService service;
	
	@GetMapping
	public ResponseEntity<List<Orcamento>> findAll(){
		List<Orcamento> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Orcamento> findById(@PathVariable Long id){
		Orcamento obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}

	@PostMapping
	@Transactional
	public ResponseEntity<Orcamento> inserirOrcamento(@RequestBody Orcamento obj) {
		obj = service.inserirOrcamento(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).body(obj);
	}
	
	@PutMapping(value = "/{id}/attStatus")
	@Transactional
	public ResponseEntity<Orcamento> atualizarStatusOrcamento(@PathVariable Long id, @RequestBody Orcamento obj){
		obj = service.atualizarStatusOrcamento(id, obj);
		return ResponseEntity.ok().body(obj);
	}

	
	@PutMapping(value = "/{id}/attOrcamento")
	@Transactional
	public ResponseEntity<Orcamento> atualizarOrcamento(@PathVariable Long id, @RequestBody Orcamento obj){
		obj = service.atualizarOrcamento(id, obj);
		return ResponseEntity.ok().body(obj);
	}
}
