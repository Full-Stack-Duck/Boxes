package com.fullstackduck.boxes.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fullstackduck.boxes.entities.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long>{

}
