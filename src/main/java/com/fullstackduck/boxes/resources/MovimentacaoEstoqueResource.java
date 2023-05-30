package com.fullstackduck.boxes.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fullstackduck.boxes.entities.MovimentacaoEstoque;
import com.fullstackduck.boxes.services.MovimentacaoEstoqueService;

import jakarta.validation.Valid;

//Controlador Rest
@RestController
@CrossOrigin(origins = "*")
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
	public ResponseEntity<MovimentacaoEstoque> inserirMovimentacaoEstoque(@Valid @RequestBody MovimentacaoEstoque obj) {
		obj = service.inserirMovimentacaoEstoque(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).body(obj);
	}
	
	@Transactional
	@PostMapping("/{produto_id}/adicionarItem")
    public ResponseEntity<String> adicionarItem(@PathVariable Long produto_id,@RequestParam Integer quantidade) {
		 try {
		        if (quantidade != null) {
		            service.adicionarItem(produto_id, quantidade.intValue());
		            return ResponseEntity.ok("Item adicionado com sucesso");
		        } else {
		            return ResponseEntity.badRequest().body("A quantidade não pode ser nula");
		        }
		    } catch (IllegalArgumentException e) {
		        return ResponseEntity.badRequest().body(e.getMessage());
		    }
	}
	

	@DeleteMapping("/{produto_id}/removerItem")
	public ResponseEntity<String> removerItem(@PathVariable Long produto_id, @RequestParam Integer quantidade) {
	    try {
	        service.removerItem(produto_id, quantidade);
	        return ResponseEntity.ok("Item removido com sucesso");
	    } catch (IllegalArgumentException e) {
	        return ResponseEntity.badRequest().body(e.getMessage());
	    }
	}



}
