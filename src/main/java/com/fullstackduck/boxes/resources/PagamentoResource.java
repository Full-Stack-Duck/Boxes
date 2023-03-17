package com.fullstackduck.boxes.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fullstackduck.boxes.entities.Pagamento;
import com.fullstackduck.boxes.services.PagamentoService;

//Controlador Rest
@RestController
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
}
