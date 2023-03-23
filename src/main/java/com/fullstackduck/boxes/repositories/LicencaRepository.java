package com.fullstackduck.boxes.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fullstackduck.boxes.entities.Licenca;

@Repository
public interface LicencaRepository extends JpaRepository<Licenca, Long>{

}
