package com.fullstackduck.boxes.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fullstackduck.boxes.entities.Pagamento;

public interface PagamentoRepository extends JpaRepository<Pagamento, Long>{

}