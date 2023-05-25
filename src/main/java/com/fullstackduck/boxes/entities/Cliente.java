package com.fullstackduck.boxes.entities;

import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fullstackduck.boxes.entities.enums.StatusCliente;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
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
	@Getter @Setter private Long id;
	
	@NotBlank
	@Getter @Setter private String nome;
	
	@NotBlank
	@Email
	@Getter @Setter private String email;
	
	
	@Getter @Setter private String telefone = null;
	@Getter @Setter private Instant dataNascimento = null;
	@Getter @Setter private String documento = null;
	@Getter @Setter private Instant dataCliente = Instant.now();
	private Integer statusCliente;
	
	//Relacionamento com a entidade de Usuario
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "usuario_id")
    @Getter @Setter private Usuario usuario;

	//Relacionamento com a entidade de Orcamentos
	@JsonIgnore
	@OneToMany(mappedBy = "cliente", fetch = FetchType.EAGER)
    @Getter private List<Orcamento> orcamentos = new ArrayList<>();
	
	//Relacionamento com a entidade de Orcamentos
	@JsonIgnore
	@OneToMany(mappedBy = "cliente", fetch = FetchType.EAGER)
    @Getter private List<Pedido> pedidos = new ArrayList<>();

	public Cliente(Long id, String nome, String email, String telefone, Instant dataNascimento, String documento,
			Instant dataCadastro, StatusCliente statusCliente, Usuario usuario) {
		super();
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.telefone = telefone;
		this.dataNascimento = dataNascimento;
		this.documento = documento;
		this.dataCliente = dataCadastro;
		setStatusCliente(statusCliente);
		this.usuario = usuario;
	}

	public StatusCliente getStatusCliente() {
		return StatusCliente.valueOf(statusCliente);
	}

	public void setStatusCliente(StatusCliente statusCliente) {
		if(statusCliente != null) {
			this.statusCliente = statusCliente.getCode();
		}
	}
	
	public void setOrcamentos(List<Orcamento> orcamentos) {
	    this.orcamentos = orcamentos;
	}

	
}
