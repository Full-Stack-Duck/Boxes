package com.fullstackduck.boxes.entities;

import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fullstackduck.boxes.entities.enums.Status;
import com.fullstackduck.boxes.entities.enums.StatusPedido;
import com.fullstackduck.boxes.entities.enums.TipoEntrega;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//Mapeamento JPA e Lombok
@Entity
@Table(name="tb_pedido")
@NoArgsConstructor
@EqualsAndHashCode(of="id")
public class Pedido implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//Atributos da classe
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Getter private Long id;
	@Getter @Setter private Double total;
	private Integer tipoEntrega;
	@Getter @Setter private Instant dataOrcamento;
	@Getter @Setter private Instant dataEntrega;
	private Integer status;
	private Integer statusPedido;

	//Relacionamento com a entidade de Usuario
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "usuario_id")
    @Getter @Setter private Usuario usuario;
	
	//Relacionamento com a entidade de Orcamento
	@OneToOne
	@JoinColumn(name = "orcamento_id")
    @Getter @Setter private Orcamento orcamento;

	//Relacionamento com a entidade de Pagamento
	@JsonIgnore
	@OneToMany(mappedBy = "pedido")
    @Getter private List<Pagamento> pagamentos = new ArrayList<>();

	public Pedido(Long id, Double total, TipoEntrega tipoEntrega, Instant dataOrcamento, Instant dataEntrega,
			Status status, StatusPedido statusPedido, Usuario usuario, Orcamento orcamento) {
		super();
		this.id = id;
		this.total = total;
		setTipoEntrega(tipoEntrega);
		this.dataOrcamento = dataOrcamento;
		this.dataEntrega = dataEntrega;
		setStatus(status);
		setStatusPedido(statusPedido);
		this.usuario = usuario;
		this.orcamento = orcamento;
	}

	public TipoEntrega getTipoEntrega() {
		return TipoEntrega.valueOf(tipoEntrega);
	}

	public void setTipoEntrega(TipoEntrega tipoEntrega) {
		if(tipoEntrega != null) {
		this.tipoEntrega = tipoEntrega.getCode();
		}
	}

	public Status getStatus() {
		return Status.valueOf(status);
	}

	public void setStatus(Status status) {
		if(status != null) {
		this.status = status.getCode();
		}
	}

	public StatusPedido getStatusPedido() {
		return StatusPedido.valueOf(statusPedido);
	}

	public void setStatusPedido(StatusPedido statusPedido) {
		if(statusPedido != null) {
		this.statusPedido = statusPedido.getCode();
		}
	}
	
}
