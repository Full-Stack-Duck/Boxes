package com.fullstackduck.boxes.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
