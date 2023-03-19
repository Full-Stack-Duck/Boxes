package com.fullstackduck.boxes.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fullstackduck.boxes.entities.enums.Status;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//Mapeamento JPA e Lombok
@Entity
@Table(name="tb_usuario")
@NoArgsConstructor
@EqualsAndHashCode(of="id")
public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//Atributos da classe
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Getter private Long id;
	@Getter @Setter private String nome;
	@Getter @Setter private String documento;
	@Getter @Setter private String email;
	@Getter @Setter private String telefone;
	@Getter @Setter private String senha;
	@Getter @Setter private String endereco;
	@Getter @Setter private String logo;
	@Getter @Setter private Status status;
	
	//Relacionamento com a entidade de Licencas
	@JsonIgnore
	@OneToMany(mappedBy = "usuario")
    @Getter private List<Licenca> licencas = new ArrayList<>();
	
	//Relacionamento com a entidade de Produtos
	@JsonIgnore
	@OneToMany(mappedBy = "usuario")
    @Getter private List<Produto> produtos = new ArrayList<>();

	//Relacionamento com a entidade de Despesas
	@JsonIgnore
	@OneToMany(mappedBy = "usuario")
    @Getter private List<Despesa> despesas = new ArrayList<>();
	
	//Relacionamento com a entidade de Receitas
	@JsonIgnore
	@OneToMany(mappedBy = "usuario")
    @Getter private List<Receita> receitas = new ArrayList<>();

	//Relacionamento com a entidade de Orcamentos
	@JsonIgnore
	@OneToMany(mappedBy = "usuario")
    @Getter private List<Orcamento> orcamentos = new ArrayList<>();
	
	//Relacionamento com a entidade de Pedidos
	@JsonIgnore
	@OneToMany(mappedBy = "usuario")
    @Getter private List<Pedido> pedidos = new ArrayList<>();
	

	public Usuario(Long id, String nome, String documento, String email, String telefone, String senha, String endereco,
			String logo, Status status) {
		super();
		this.id = id;
		this.nome = nome;
		this.documento = documento;
		this.email = email;
		this.telefone = telefone;
		this.senha = senha;
		this.endereco = endereco;
		this.logo = logo;
		this.status = status;
	}
}
