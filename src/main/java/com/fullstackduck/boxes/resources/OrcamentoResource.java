package com.fullstackduck.boxes.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
