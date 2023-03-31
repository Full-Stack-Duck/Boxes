package com.fullstackduck.boxes.repositories;

import java.time.Instant;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fullstackduck.boxes.entities.Despesa;

@Repository
public interface DespesaRepository extends JpaRepository<Despesa, Long>{
	
	@Query("SELECT d FROM Despesa d WHERE d.categoria = :categoria")
    List<Despesa> findByCategoria(@Param("categoria") int i);

	List<Despesa> findByDataDespesaBetween(Instant dataInicio, Instant dataFim);


}
