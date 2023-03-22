package com.fullstackduck.boxes.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fullstackduck.boxes.entities.Catalogo;

public interface CatalogoRepository extends JpaRepository<Catalogo, Long>{

}
