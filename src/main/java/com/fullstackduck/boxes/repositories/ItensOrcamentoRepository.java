package com.fullstackduck.boxes.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fullstackduck.boxes.entities.ItensOrcamento;

@Repository
public interface ItensOrcamentoRepository extends JpaRepository<ItensOrcamento, Long>{

}