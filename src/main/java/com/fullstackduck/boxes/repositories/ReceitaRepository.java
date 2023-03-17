package com.fullstackduck.boxes.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fullstackduck.boxes.entities.Receita;

public interface ReceitaRepository extends JpaRepository<Receita, Long>{

}
