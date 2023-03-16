package com.fullstackduck.boxes.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fullstackduck.boxes.entities.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long>{

}
