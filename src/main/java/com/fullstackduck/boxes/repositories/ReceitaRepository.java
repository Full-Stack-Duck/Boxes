package com.fullstackduck.boxes.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fullstackduck.boxes.entities.Receita;

@Repository
public interface ReceitaRepository extends JpaRepository<Receita, Long>{

}
