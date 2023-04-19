package com.fullstackduck.boxes.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fullstackduck.boxes.entities.MovimentacaoEstoque;

@Repository
public interface MovimentacaoEstoqueRepository extends JpaRepository<MovimentacaoEstoque, Long>{                                                   
	
}
