package com.fullstackduck.boxes.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fullstackduck.boxes.entities.Estoque;

@Repository
public interface EstoqueRepository extends JpaRepository<Estoque, Long>{

}
