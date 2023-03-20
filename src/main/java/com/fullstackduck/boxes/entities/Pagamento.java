package com.fullstackduck.boxes.entities;

import java.io.Serializable;
import java.time.Instant;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fullstackduck.boxes.entities.enums.FormaPagamento;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//Mapeamento JPA e Lombok
@Entity
@Table(name="tb_pagamento")
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

	//Relacionamento com a entidade de Pedido
	@ManyToOne
	@JoinColumn(name = "pedido_id")
    @Getter @Setter private Pedido pedidos;

	//Relacionamento com a entidade de Receita
	@JsonIgnore
	@OneToOne(mappedBy = "pagamento")
	@Getter @Setter private Receita receita;

	public Pagamento(Long id, Double valor, Instant dataPagamento, FormaPagamento formaPagamento, Pedido pedidos) {
		super();
		this.id = id;
		this.valor = valor;
		this.dataPagamento = dataPagamento;
		this.formaPagamento = formaPagamento;
		this.pedidos = pedidos;
	}
}
