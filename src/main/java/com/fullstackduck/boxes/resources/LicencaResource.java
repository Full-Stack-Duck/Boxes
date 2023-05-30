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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fullstackduck.boxes.entities.Licenca;
import com.fullstackduck.boxes.services.LicencaService;
import com.fullstackduck.boxes.services.UsuarioService;

import jakarta.validation.Valid;

//Controlador Rest
@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/licencas")
public class LicencaResource {

	@Autowired
	private LicencaService service;
	
	
	@GetMapping
	public ResponseEntity<List<Licenca>> findAll(){
		List<Licenca> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Licenca> findById(@PathVariable Long id){
		Licenca obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@PostMapping(value = "/inserirLicenca/{usuarioId}")
	public ResponseEntity<Licenca> inserirLicenca(@Valid @RequestBody Licenca obj, @PathVariable Integer usuarioId) {
	    obj = service.inserirLicenca(obj, usuarioId);
	    URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
	    return ResponseEntity.created(uri).body(obj);
	}
	
	@PutMapping(value = "/{id}/renovarLicenca")
	public ResponseEntity<Licenca> renovarLicenca(@PathVariable Long id, @RequestBody Licenca obj){
		obj = service.renovarLicenca(id, obj);
		return ResponseEntity.ok().body(obj);
	}
	
	@PostMapping(value = "/alterarLicenca/{usuarioId}")
	public ResponseEntity<Licenca> alterarLicenca(@Valid @RequestBody Licenca obj, @PathVariable Integer usuarioId){
		obj = service.alterarLicenca(obj, usuarioId);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).body(obj);
	}
}
