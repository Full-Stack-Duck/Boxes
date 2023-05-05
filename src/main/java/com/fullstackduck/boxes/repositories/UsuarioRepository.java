package com.fullstackduck.boxes.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fullstackduck.boxes.entities.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>{ 
	
	Usuario findByEmail(String email);

	Usuario getReferenceById(Integer id);
	
}
