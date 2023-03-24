package com.fullstackduck.boxes.repositories;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.fullstackduck.boxes.entities.Usuario;

@DataJpaTest
public class UsuarioRepositoryTests {
	
	@Autowired
	private UsuarioRepository repository;
	
	@Test
	public void criarDeveCriarUsuario() {
		
		long exintingId = 1L;
		
		repository.deleteById(exintingId);
		
		Optional<Usuario> result = repository.findById(exintingId);
		Assertions.assertFalse(result.isPresent());
				
	}
	
	

}
