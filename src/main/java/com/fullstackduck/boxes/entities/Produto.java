package com.fullstackduck.boxes.entities;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fullstackduck.boxes.entities.enums.Status;
import com.fullstackduck.boxes.entities.enums.TipoArmazenamento;
import com.fullstackduck.boxes.entities.enums.TipoProduto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//Mapeamento JPA e Lombok
@Entity
@Table(name="tb_produto")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of="id")
public class Produto implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//Atributos da classe
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Getter private Long id;
	@Getter @Setter private String nome;
	@Getter @Setter private Double valor;
	@Getter @Setter private TipoArmazenamento categoria;
	@Getter @Setter private Integer estoque;
	@Getter @Setter private TipoProduto tipo;
	@Getter @Setter private Status status;
	@Getter @Setter private String observacao;
	
	//Relacionamento com a entidade de Usuario
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "usuario_id")
    @Getter @Setter private Usuario usuario;
	
	//Relacionamento com a entidade de Estoque
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "catalogo_id")
    @Getter @Setter private Catalogo catalogo;
}