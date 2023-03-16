package com.fullstackduck.boxes.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fullstackduck.boxes.entities.Estoque;

public interface EstoqueRepository extends JpaRepository<Estoque, Long>{

}
