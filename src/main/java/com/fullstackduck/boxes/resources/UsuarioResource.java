package com.fullstackduck.boxes.resources;

import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fullstackduck.boxes.entities.Cliente;
import com.fullstackduck.boxes.entities.Produto;
import com.fullstackduck.boxes.entities.Usuario;
import com.fullstackduck.boxes.services.UsuarioService;

import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "*")
@EnableAsync
@RequestMapping(value = "/usuarios")
public class UsuarioResource {

	@Autowired
	private UsuarioService service;
	
	@GetMapping
	public CompletableFuture<ResponseEntity<List<Usuario>>> findAll() {
		return service.findAll().thenApply(ResponseEntity::ok);
	}
	
	@GetMapping(value = "/{id}")
	public CompletableFuture<ResponseEntity<Usuario>> findById(@PathVariable Long id){
		return service.findById(id).thenApply(ResponseEntity::ok);
	}
	
	@PostMapping
	@Transactional
	public CompletableFuture<ResponseEntity<Usuario>> inserirUsuario(@RequestBody Usuario obj) {
		return service.inserirUsuario(obj).thenApply(usuario -> {
			URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(usuario.getId()).toUri();
			return ResponseEntity.created(uri).body(usuario);
		});
	}
	
	@PutMapping(value = "/{id}/attStatus")
	@Transactional
	public CompletableFuture<ResponseEntity<Usuario>> atualizarStatusUsuario(@PathVariable Long id, @RequestBody Usuario obj){
		return service.atualizarStatusUsuario(id, obj).thenApply(ResponseEntity::ok);
	}
	
	@PutMapping(value = "/{id}/attUsuario")
	@Transactional
	public CompletableFuture<ResponseEntity<Usuario>> atualizarUsuario(@PathVariable Long id, @RequestBody Usuario obj){
		return service.atualizarUsuario(id, obj).thenApply(ResponseEntity::ok);
	}
	
	@PostMapping(value = "/{id}/validar-senha")
	public CompletableFuture<ResponseEntity<Boolean>> validarSenha(@PathVariable String email, @RequestBody String senha) {
		return service.validarSenha(email, senha).thenApply(ResponseEntity::ok);
	}
	
	@PostMapping(value = "/recuperar-senha")
	public CompletableFuture<ResponseEntity<Void>> recuperarSenha(@RequestBody String email) {
		return service.recuperarSenha(email).thenApply(s -> ResponseEntity.noContent().build());
	}
	
	@GetMapping(value = "/{id}/clientes")
	public CompletableFuture<ResponseEntity<List<Cliente>>> listarClientes(@PathVariable Long id) {
		return service.listarClientes(id).thenApply(ResponseEntity::ok);
	}
	
	@GetMapping(value = "/{id}/produtos")
	public CompletableFuture<ResponseEntity<List<Produto>>> listarProdutos(@PathVariable Long id) {
		return service.listarProdutos(id).thenApply(ResponseEntity::ok);
	}
	
	@PostMapping(value = "/login")
	public CompletableFuture<ResponseEntity<Usuario>> login(@RequestBody Map<String, String> requestMap) throws Exception {
		String email = requestMap.get("email");
		String senha = requestMap.get("senha");
		return service.login(email, senha).thenApply(ResponseEntity::ok).exceptionally(e -> ResponseEntity.badRequest().build());
	}
}
