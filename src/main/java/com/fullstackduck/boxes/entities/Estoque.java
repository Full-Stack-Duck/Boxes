package com.fullstackduck.boxes.entities;

import java.io.Serializable;

import com.fullstackduck.boxes.entities.enums.TipoProduto;

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
@Table(name="tb_estoque")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of="id")
public class Estoque implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//Atributos da classe
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Getter private Long id;
	@Getter @Setter private Integer quantidade;
	@Getter @Setter private String nome;
	@Getter @Setter private TipoProduto tipo;
	@Getter @Setter private Double valor;
}
