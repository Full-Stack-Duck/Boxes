package com.fullstackduck.boxes.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fullstackduck.boxes.entities.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long>{

}
