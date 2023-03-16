package com.fullstackduck.boxes.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fullstackduck.boxes.entities.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

}
