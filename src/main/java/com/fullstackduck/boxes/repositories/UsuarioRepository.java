package com.fullstackduck.boxes.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fullstackduck.boxes.entities.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>{                                                   

	 boolean existsByEmail(String email);
	 @Query("SELECT COUNT(u) > 0 FROM Usuario u WHERE u.email = :email AND u.id != :id")
	    boolean existsByEmailAndIdNot(@Param("email") String email, @Param("id") Long id);
}
