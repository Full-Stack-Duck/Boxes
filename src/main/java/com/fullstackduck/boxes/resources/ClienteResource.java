package com.fullstackduck.boxes.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fullstackduck.boxes.entities.Cliente;
import com.fullstackduck.boxes.services.ClienteService;

//Controlador Rest
@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/clientes")
public class ClienteResource {

	@Autowired
	private ClienteService service;
	
	
	@GetMapping
	public ResponseEntity<List<Cliente>> findAll(){
		List<Cliente> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Cliente> findById(@PathVariable Long id){
		Cliente obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}

	
	@PostMapping
	@Transactional
		public ResponseEntity<Cliente> inserirCliente(@RequestBody Cliente obj) {
		obj = service.inserirCliente(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).body(obj);
	}
	

	@PutMapping(value = "/{id}/attStatusCliente")
	@Transactional
	public ResponseEntity<Cliente> atualizarStatusCliente(@PathVariable Long id, @RequestBody Cliente obj){
		obj = service.atualizarStatusCliente(id, obj);
		return ResponseEntity.ok().body(obj);
	}
	
	
	@PutMapping(value = "/{id}/attCliente")
	@Transactional
	public ResponseEntity<Cliente> atualizarCliente(@PathVariable Long id, @RequestBody Cliente obj){
		obj = service.atualizarCliente(id, obj);
		return ResponseEntity.ok().body(obj);
	}
	
	@GetMapping(value = "/{id}/orcamentos")
	public ResponseEntity<List<Cliente>> listarClientes(@PathVariable Long id) {
	    List<Cliente> clientes = service.listarClientes(id);
	    return ResponseEntity.ok().body(clientes);
	}
	
	@GetMapping(value = "/{id}/orcamentospd")
	public List<Cliente> listarClientesPeriodo(@PathVariable Long id,@RequestParam String dataInicio, @RequestParam String dataFim){
		List<Cliente> clientes = service.listarClientePeriodo(dataInicio, dataFim);
		return clientes;
	}
}
