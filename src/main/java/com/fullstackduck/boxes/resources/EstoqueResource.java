package com.fullstackduck.boxes.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fullstackduck.boxes.entities.Estoque;
import com.fullstackduck.boxes.services.EstoqueService;

//Controlador Rest
@RestController
@RequestMapping(value = "/estoques")
public class EstoqueResource {

	@Autowired
	private EstoqueService service;
	
	@GetMapping
	public ResponseEntity<List<Estoque>> findAll(){
		List<Estoque> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Estoque> findById(@PathVariable Long id){
		Estoque obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
}
