package com.fullstackduck.boxes.repositories;

import java.time.Instant;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fullstackduck.boxes.entities.Orcamento;

@Repository
public interface OrcamentoRepository extends JpaRepository<Orcamento, Long>{

	List<Orcamento> findByDataOrcamentoBetween(Instant dataInicio, Instant dataFim);

}
