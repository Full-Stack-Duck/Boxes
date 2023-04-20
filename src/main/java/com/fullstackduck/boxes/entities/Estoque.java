package com.fullstackduck.boxes.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//Mapeamento JPA e Lombok
@Entity
@Table(name="tb_estoque")
@NoArgsConstructor
@EqualsAndHashCode(of="id")
public class Estoque implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//Atributos da classe
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Getter private Long id;
	@Getter @Setter private String nome;
	
	//Relacionamento com a entidade de Produtos
	@OneToMany(mappedBy = "estoque", fetch = FetchType.EAGER)
    @Getter private List<Produto> produtos = new ArrayList<>();
	
	//Relacionamento com a entidade de Usuario
	@OneToOne
	@JoinColumn(name = "usuario_id")
    @Getter @Setter private Usuario usuario;
	
	//Relacionamento com a entidade de MovimentacaoEstoque
	@OneToMany(mappedBy = "estoque", fetch = FetchType.EAGER)
	private List<MovimentacaoEstoque> movimentacoes;

	public Estoque(Long id, String nome, Usuario usuario) {
		super();
		this.id = id;
		this.nome = nome;
		this.usuario = usuario;
	}
	
	
}