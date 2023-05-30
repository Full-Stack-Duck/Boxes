package com.fullstackduck.boxes.resources;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fullstackduck.boxes.entities.ItensOrcamento;
import com.fullstackduck.boxes.entities.Orcamento;
import com.fullstackduck.boxes.entities.Pedido;
import com.fullstackduck.boxes.services.ItensOrcamentoService;
import com.fullstackduck.boxes.services.OrcamentoService;
import com.fullstackduck.boxes.services.exceptions.EstoqueInsuficienteException;

import jakarta.validation.Valid;

//Controlador Rest
@RestController
@CrossOrigin(origins = "*")
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
	public Orcamento inserirOrcamento(@Valid @RequestBody Orcamento obj) {
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
	
	@PostMapping("/{id}/gerar-pedido")
	public ResponseEntity<String> gerarPedido(@PathVariable Long id) throws JsonProcessingException {
	    Orcamento orcamento = service.findById(id);
	    try {
	        service.gerarPedido(orcamento);
	        String sucessMessage = "Pedido gerado com sucesso.";
	        // Criar um objeto Map para encapsular a mensagem de erro
	        Map<String, String> doneMap = new HashMap<>();
	        doneMap.put("Feito", sucessMessage);
	        // Converter o Map para uma string JSON usando o ObjectMapper do Jackson
	        ObjectMapper objectMapper = new ObjectMapper();
	        String json = objectMapper.writeValueAsString(doneMap);
	        return ResponseEntity.status(HttpStatus.CREATED).body(json);
	    } catch (EstoqueInsuficienteException e) {
	        String errorMessage = e.getMessage();
	        // Criar um objeto Map para encapsular a mensagem de erro
	        Map<String, String> errorMap = new HashMap<>();
	        errorMap.put("error", errorMessage);
	        // Converter o Map para uma string JSON usando o ObjectMapper do Jackson
	        ObjectMapper objectMapper = new ObjectMapper();
	        String json = objectMapper.writeValueAsString(errorMap);
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(json);
	    }
	}
}
