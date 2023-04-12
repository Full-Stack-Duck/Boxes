package com.fullstackduck.boxes.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fullstackduck.boxes.entities.Licenca;
import com.fullstackduck.boxes.services.LicencaService;

//Controlador Rest
@RestController
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
	
	@PostMapping
	public ResponseEntity<Licenca> create(@RequestBody Licenca licenca) {
	    Licenca createdLicenca = service.save(licenca);
	    URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
	            .path("/{id}")
	            .buildAndExpand(createdLicenca.getId())
	            .toUri();
	    return ResponseEntity.created(uri).body(createdLicenca);
	}
	
	

	
	
}
