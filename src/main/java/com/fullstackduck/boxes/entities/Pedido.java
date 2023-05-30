package com.fullstackduck.boxes.entities;

import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fullstackduck.boxes.entities.enums.Status;
import com.fullstackduck.boxes.entities.enums.StatusPagamentoPedido;
import com.fullstackduck.boxes.entities.enums.StatusPedido;
import com.fullstackduck.boxes.entities.enums.TipoEntrega;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
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
	@Getter @Setter private Long id;
	
	@Getter @Setter private Double total;
	
	private Integer tipoEntrega;
	@Getter @Setter private Instant dataPedido;
	@Getter @Setter private Instant dataEntrega;
	private Integer status;
	private Integer statusPedido;
	private Integer statusPagamentoPedido;

	//Relacionamento com a entidade de Usuario
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "usuario_id")
    @Getter @Setter private Usuario usuario;
	
	//Relacionamento com a entidade de Usuario
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "cliente_id")
    @Getter @Setter private Cliente cliente;
	
	//Relacionamento com a entidade de Orcamento
	@OneToOne
	@JsonIgnore
	@JoinColumn(name = "orcamento_id")
    @Getter @Setter private Orcamento orcamento;

	//Relacionamento com a entidade de Pagamento
	@JsonIgnore
	@OneToMany(mappedBy = "pedido", fetch = FetchType.EAGER)
    @Getter private List<Pagamento> pagamentos = new ArrayList<>();

	public Pedido(Long id, Double total, TipoEntrega tipoEntrega, Instant dataPedido, Instant dataEntrega,
			Status status, StatusPedido statusPedido, StatusPagamentoPedido statusPagamentoPedido, Usuario usuario, Cliente cliente, Orcamento orcamento) {
		super();
		this.id = id;
		this.total = total;
		setTipoEntrega(tipoEntrega);
		this.dataPedido = dataPedido;
		this.dataEntrega = dataEntrega;
		setStatus(status);
		setStatusPedido(statusPedido);
		setStatusPagamentoPedido(statusPagamentoPedido);
		this.usuario = usuario;
		this.cliente = cliente;
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
	
	public StatusPagamentoPedido getStatusPagamentoPedido() {
		return StatusPagamentoPedido.valueOf(statusPagamentoPedido);
	}

	public void setStatusPagamentoPedido(StatusPagamentoPedido statusPagamentoPedido) {
		if(statusPagamentoPedido != null) {
		this.statusPagamentoPedido = statusPagamentoPedido.getCode();
		}
	}
	
}
