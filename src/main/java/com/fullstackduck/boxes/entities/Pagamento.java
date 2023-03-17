package com.fullstackduck.boxes.entities;

import java.io.Serializable;
import java.time.Instant;

import com.fullstackduck.boxes.entities.enums.FormaPagamento;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//Mapeamento JPA e Lombok
@Entity
@Table(name="tb_pagamento")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of="id")
public class Pagamento implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//Atributos da classe
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Getter private Long id;
	@Getter @Setter private Double valor;
	@Getter @Setter private Instant dataPagamento;
	@Getter @Setter private FormaPagamento formaPagamento;

}
