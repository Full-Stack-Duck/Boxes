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

import com.fullstackduck.boxes.entities.Orcamento;
import com.fullstackduck.boxes.entities.Produto;
import com.fullstackduck.boxes.entities.enums.TipoArmazenamento;
import com.fullstackduck.boxes.entities.enums.TipoProduto;
import com.fullstackduck.boxes.services.ProdutoService;

import jakarta.validation.Valid;

//Controlador Rest
@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/produtos")
public class ProdutoResource {

	@Autowired
	private ProdutoService service;
	
	
	@GetMapping
	public ResponseEntity<List<Produto>> findAll(){
		List<Produto> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Produto> findById(@PathVariable Long id){
		Produto obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<Produto> inserirProduto(@Valid @RequestBody Produto obj) {
		obj = service.inserirProduto(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).body(obj);
	}
	
	@PutMapping(value = "/{id}/attStatusProduto")
	@Transactional
	public ResponseEntity<Produto> atualizarStatusProduto(@PathVariable Long id, @RequestBody Produto obj){
		obj = service.atualizarStatusProduto(id, obj);
		return ResponseEntity.ok().body(obj);
	}
	
	@PutMapping(value = "/{id}/attProduto")
	@Transactional
	public ResponseEntity<Produto> atualizarProduto(@PathVariable Long id, @RequestBody Produto obj){
		obj = service.atualizarProduto(id, obj);
		return ResponseEntity.ok().body(obj);
	}
	
	@GetMapping(value = "/{id}/produtos")
	public ResponseEntity<List<Produto>> listarProdutos(@PathVariable Long id) {
	    List<Produto> produtos = service.listarProdutos(id);
	    return ResponseEntity.ok().body(produtos);
	}
	
	@GetMapping(value = "/categoria")
	public ResponseEntity<List<Produto>> listarProdutosCategoria(@RequestParam(value = "categoria") TipoArmazenamento categoria) {
		List<Produto> produtos = service.listarProdutosCategoria(categoria);
		return ResponseEntity.ok().body(produtos);
	}
	
	@GetMapping(value = "/tipo")
	public ResponseEntity<List<Produto>> listarProdutosTipo(@RequestParam(value = "tipo") TipoProduto tipo) {
		List<Produto> produtos = service.listarProdutosTipo(tipo);
		return ResponseEntity.ok().body(produtos);
	}
}
