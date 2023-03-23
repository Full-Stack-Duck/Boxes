package com.fullstackduck.boxes.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fullstackduck.boxes.entities.Catalogo;

@Repository
public interface CatalogoRepository extends JpaRepository<Catalogo, Long>{

}
