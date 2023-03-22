package com.fullstackduck.boxes.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fullstackduck.boxes.entities.Catalogo;
import com.fullstackduck.boxes.services.CatalogoService;

//Controlador Rest
@RestController
@RequestMapping(value = "/catalogos")
public class CatalogoResource {

	@Autowired
	private CatalogoService service;
	
	@GetMapping
	public ResponseEntity<List<Catalogo>> findAll(){
		List<Catalogo> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Catalogo> findById(@PathVariable Long id){
		Catalogo obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
}
