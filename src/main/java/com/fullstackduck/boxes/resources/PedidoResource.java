package com.fullstackduck.boxes.resources;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fullstackduck.boxes.entities.Pedido;
import com.fullstackduck.boxes.services.PedidoService;
import com.fullstackduck.boxes.services.exceptions.EstoqueInsuficienteException;

//Controlador Rest
@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/pedidos")
public class PedidoResource {

	@Autowired
	private PedidoService service;
	
	@GetMapping
	public ResponseEntity<List<Pedido>> findAll(){
		List<Pedido> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Pedido> findById(@PathVariable Long id){
		Pedido obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@GetMapping(value = "/{id}/pedidos")
	public ResponseEntity<List<Pedido>> listarOrcamentos(@PathVariable Long id) {
	    List<Pedido> pedidos = service.listarPedidos(id);
	    return ResponseEntity.ok().body(pedidos);
	}
	
	@GetMapping(value = "/{id}/orcamentospd")
	public List<Pedido> listarOrcamentosPeriodo(@PathVariable Long id,@RequestParam String dataInicio, @RequestParam String dataFim){
		List<Pedido> pedidos = service.listarPedidosPeriodo(dataInicio, dataFim);
		return pedidos;
	}
	
	@PutMapping(value = "/{id}/status-pedido")
	public ResponseEntity<Pedido> atualizarStatusPedido(@PathVariable Long id, @RequestBody Pedido obj) {
	    Pedido pedido = service.atualizarStatusPedido(id, obj);
	    return ResponseEntity.ok().body(pedido);
	}

	 @PutMapping(value = "/{id}/status-pagamento")
	 public ResponseEntity<Pedido> atualizarStatusPagamentoPedido(@PathVariable Long id, @RequestBody Pedido obj) {
	     Pedido pedido = service.atualizarStatusPagamentoPedido(id, obj);
	     return ResponseEntity.ok().body(pedido);
	 }
	 
	 @PutMapping(value = "/{id}/cancelarPedido")
	 public ResponseEntity<String> cancelarPedido(@PathVariable Long id) throws JsonProcessingException{
        service.cancelarPedido(id);
        String sucessMessage = "Pedido cancelado";
        Map<String, String> doneMap = new HashMap<>();
        doneMap.put("Cancelado", sucessMessage);
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(doneMap);
        return ResponseEntity.status(HttpStatus.CREATED).body(json);
	 }    
}
