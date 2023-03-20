package com.fullstackduck.boxes.entities;

import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fullstackduck.boxes.entities.enums.StatusCliente;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//Mapeamento JPA e Lombok
@Entity
@Table(name="tb_cliente")
@NoArgsConstructor
@EqualsAndHashCode(of="id")
public class Cliente implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//Atributos da classe
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Getter private Long id;
	@Getter @Setter private String nome;
	@Getter @Setter private String email;
	@Getter @Setter private String telefone;
	@Getter @Setter private Instant dataNascimento;
	@Getter @Setter private String documento;
	@Getter @Setter private Instant dataCadastro;
	@Getter @Setter private StatusCliente statusCliente;
	
	//Relacionamento com a entidade de Usuario
	@ManyToOne
	@JoinColumn(name = "usuario_id")
    @Getter @Setter private Usuario usuario;

	//Relacionamento com a entidade de Orcamentos
	@JsonIgnore
	@OneToMany(mappedBy = "cliente")
    @Getter private List<Orcamento> orcamentos = new ArrayList<>();

	public Cliente(Long id, String nome, String email, String telefone, Instant dataNascimento, String documento,
			Instant dataCadastro, StatusCliente statusCliente, Usuario usuario) {
		super();
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.telefone = telefone;
		this.dataNascimento = dataNascimento;
		this.documento = documento;
		this.dataCadastro = dataCadastro;
		this.statusCliente = statusCliente;
		this.usuario = usuario;
	}
}
