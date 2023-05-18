package com.fullstackduck.boxes.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fullstackduck.boxes.entities.Produto;
import com.fullstackduck.boxes.entities.enums.TipoArmazenamento;
import com.fullstackduck.boxes.entities.enums.TipoProduto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long>{
	
    List<Produto> findByTipo(TipoProduto tipo);
	List<Produto> findByCategoria(TipoArmazenamento categoria);
	Produto getReferenceById(Integer produtoId);

}
