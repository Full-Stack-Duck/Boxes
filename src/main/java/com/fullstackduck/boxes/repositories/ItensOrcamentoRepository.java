package com.fullstackduck.boxes.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fullstackduck.boxes.entities.ItensOrcamento;
import com.fullstackduck.boxes.entities.Orcamento;
import com.fullstackduck.boxes.entities.pk.ItensOrcamentoPK;

@Repository
public interface ItensOrcamentoRepository extends JpaRepository<ItensOrcamento, Long>{

	Optional<ItensOrcamento> findById(ItensOrcamentoPK id);

}
