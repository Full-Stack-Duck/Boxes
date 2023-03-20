package com.fullstackduck.boxes.entities;

import java.io.Serializable;
import java.time.Instant;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fullstackduck.boxes.entities.enums.Status;
import com.fullstackduck.boxes.entities.enums.TipoEntrega;

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
@Table(name="tb_orcamento")
@NoArgsConstructor
@EqualsAndHashCode(of="id")
public class Orcamento implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//Atributos da classe
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Getter private Long id;
	@Getter @Setter private TipoEntrega tipoEntrega;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
	@Getter @Setter private Instant dataOrcamento;
	@Getter @Setter Status status;

	//Relacionamento com a entidade de Usuario
	@ManyToOne
	@JoinColumn(name = "usuario_id")
    @Getter @Setter private Usuario usuario;
	
	//Relacionamento com a entidade de Cliente
	@ManyToOne
	@JoinColumn(name = "cliente_id")
    @Getter @Setter private Cliente cliente;

	//Relacionamento com a entidade de Pedido
	@JsonIgnore
	@OneToOne(mappedBy = "orcamento")
	@Getter @Setter private Pedido pedido;

	public Orcamento(Long id, TipoEntrega tipoEntrega, Instant dataOrcamento, Status status, Usuario usuario,
			Cliente cliente) {
		super();
		this.id = id;
		this.tipoEntrega = tipoEntrega;
		this.dataOrcamento = dataOrcamento;
		this.status = status;
		this.usuario = usuario;
		this.cliente = cliente;
	}
	
}
