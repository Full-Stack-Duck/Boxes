package com.fullstackduck.boxes.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fullstackduck.boxes.entities.MovimentacaoEstoque;
import com.fullstackduck.boxes.services.MovimentacaoEstoqueService;

//Controlador Rest
@RestController
@RequestMapping(value = "/movimentacoes")
public class MovimentacaoEstoqueResource {

	@Autowired
	private MovimentacaoEstoqueService service;
	
	@GetMapping
	public ResponseEntity<List<MovimentacaoEstoque>> findAll(){
		List<MovimentacaoEstoque> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<MovimentacaoEstoque> findById(@PathVariable Long id){
		MovimentacaoEstoque obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<MovimentacaoEstoque> inserirMovimentacaoEstoque(@RequestBody MovimentacaoEstoque obj) {
		obj = service.inserirMovimentacaoEstoque(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).body(obj);
	}
}
