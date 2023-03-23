package com.fullstackduck.boxes.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fullstackduck.boxes.entities.Despesa;

@Repository
public interface DespesaRepository extends JpaRepository<Despesa, Long>{

}
