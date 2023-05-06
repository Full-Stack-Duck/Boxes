package com.fullstackduck.boxes.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fullstackduck.boxes.entities.ItensOrcamento;
import com.fullstackduck.boxes.entities.Orcamento;
import com.fullstackduck.boxes.services.ItensOrcamentoService;
import com.fullstackduck.boxes.services.OrcamentoService;

//Controlador Rest
@RestController
@RequestMapping(value = "/orcamentos")
public class OrcamentoResource {

	@Autowired
	private OrcamentoService service;
	
	@Autowired
	private ItensOrcamentoService itensService;
	
	@GetMapping
	public List<Orcamento> findAll(){
		return service.findAll();
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Orcamento> findById(@PathVariable Long id){
		Orcamento obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@Transactional
	public Orcamento inserirOrcamento(@RequestBody Orcamento obj) {
		service.calcularTotal(obj);
		return obj;
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
	
	@PutMapping(value = "/{id}/adicionarItem/{produtoId}")
	@Transactional
	public ResponseEntity<Orcamento> adicionarItem(@PathVariable Long id, @PathVariable Integer produtoId, @RequestBody ItensOrcamento item) {
		Orcamento orcamento = service.adicionarItem(id, produtoId, item);
		service.calcularTotal(orcamento);
        return ResponseEntity.ok().body(orcamento);
	  }
	
	@DeleteMapping(value = "/{id}")
	@Transactional
	public ResponseEntity<Orcamento> removerItem(@PathVariable Long id, @PathVariable Long produtoId) {
		Orcamento orcamento = service.removerItem(id, produtoId);
		service.calcularTotal(orcamento);
        return ResponseEntity.ok().body(orcamento);
	  }
	
	@GetMapping(value = "/{id}/orcamentos")
	public ResponseEntity<List<Orcamento>> listarOrcamentos(@PathVariable Long id) {
	    List<Orcamento> orcamentos = service.listarOrcamentos(id);
	    return ResponseEntity.ok().body(orcamentos);
	}
	
	@GetMapping(value = "/{id}/orcamentospd")
	public List<Orcamento> listarOrcamentosPeriodo(@PathVariable Long id,@RequestParam String dataInicio, @RequestParam String dataFim){
		List<Orcamento> orcamentos = service.listarOrcamentoPeriodo(dataInicio, dataFim);
		return orcamentos;
	}
}
