	package com.fullstackduck.boxes.resources;
	
	import java.net.URI;
import java.util.List;
import java.util.Map;

import javax.security.auth.login.LoginException;

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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fullstackduck.boxes.entities.Cliente;
import com.fullstackduck.boxes.entities.Produto;
import com.fullstackduck.boxes.entities.Usuario;
import com.fullstackduck.boxes.services.UsuarioService;

import jakarta.validation.Valid;
	
	//Controlador Rest
	@RestController
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/usuarios")
	public class UsuarioResource {
	
		@Autowired
		private UsuarioService service;
		
		@GetMapping
		public ResponseEntity<List<Usuario>> findAll(){
			List<Usuario> list = service.findAll();
			return ResponseEntity.ok().body(list);
		}
		
		@GetMapping(value = "/{id}")
		public ResponseEntity<Usuario> findById(@PathVariable Long id){
			Usuario obj = service.findById(id);
			return ResponseEntity.ok().body(obj);
		}
		
		@PostMapping
		@Transactional
		public ResponseEntity<Usuario> inserirUsuario(@Valid @RequestBody Usuario obj) {
			obj = service.inserirUsuario(obj);
			URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
			return ResponseEntity.created(uri).body(obj);
		}
		
		@PutMapping(value = "/{id}/attStatus")
		@Transactional
		public ResponseEntity<Usuario> atualizarStatusUsuario(@PathVariable Long id, @RequestBody Usuario obj){
			obj = service.atualizarStatusUsuario(id, obj);
			return ResponseEntity.ok().body(obj);
		}
		
		
		@PutMapping(value = "/{id}/attUsuario")
		@Transactional
		public ResponseEntity<Usuario> atualizarUsuario(@PathVariable Long id, @RequestBody Usuario obj){
			obj = service.atualizarUsuario(id, obj);
			return ResponseEntity.ok().body(obj);
		}
		
		@PostMapping(value = "/{id}/validar-senha")
		public ResponseEntity<Boolean> validarSenha(@PathVariable String email, @RequestBody String senha) {
		    Boolean senhaValida = service.validarSenha(email, senha);
		    return ResponseEntity.ok().body(senhaValida);
		}
		
		@PostMapping(value = "/recuperar-senha")
		public ResponseEntity<Void> recuperarSenha(@RequestBody String email) {
		    service.recuperarSenha(email);
		    return ResponseEntity.noContent().build();
		}
		
		@GetMapping(value = "/{id}/clientes")
		public ResponseEntity<List<Cliente>> listarClientes(@PathVariable Long id) {
		    List<Cliente> clientes = service.listarClientes(id);
		    return ResponseEntity.ok().body(clientes);
		}
		
		@GetMapping(value = "/{id}/produtos")
		public ResponseEntity<List<Produto>> listarProdutos(@PathVariable Long id) {
		    List<Produto> produtos = service.listarProdutos(id);
		    return ResponseEntity.ok().body(produtos);
		}
		
		@PostMapping(value = "/login")
	    public ResponseEntity<Usuario> login(@RequestBody Map<String, String> requestMap) throws Exception {
	        try {
	            String email = requestMap.get("email");
	            String senha = requestMap.get("senha");
	            Usuario usuario = service.login(email, senha);
	            return ResponseEntity.ok().body(usuario);
	        } catch (LoginException e) {
	            return ResponseEntity.badRequest().build();
	        }
	    }
	}
