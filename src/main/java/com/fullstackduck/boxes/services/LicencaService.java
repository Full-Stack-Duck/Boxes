package com.fullstackduck.boxes.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fullstackduck.boxes.entities.Licenca;
import com.fullstackduck.boxes.repositories.LicencaRepository;

@Service //Registro de componente
public class LicencaService {

	@Autowired
	private LicencaRepository repository;
	
	public List<Licenca> findAll(){
		return repository.findAll();
	}
	
	public Licenca findById(Long id) {
		Optional<Licenca> obj = repository.findById(id);
		return obj.get();
	}
}
