package com.fullstackduck.boxes.repositories;

import java.time.Instant;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fullstackduck.boxes.entities.Orcamento;
import com.fullstackduck.boxes.entities.Receita;

@Repository
public interface ReceitaRepository extends JpaRepository<Receita, Long>{

	List<Receita> findByDataReceitaBetween(Instant data1, Instant data2);

}
