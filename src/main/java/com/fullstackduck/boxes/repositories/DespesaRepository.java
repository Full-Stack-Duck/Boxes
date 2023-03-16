package com.fullstackduck.boxes.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fullstackduck.boxes.entities.Despesa;

public interface DespesaRepository extends JpaRepository<Despesa, Long>{

}
