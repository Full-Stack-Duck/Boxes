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
	@Getter @Setter private TipoEntrega tipoEntrega;
	@Getter @Setter private Instant dataOrcamento;
	@Getter @Setter private Instant dataEntrega;
	@Getter @Setter private Status status;
	@Getter @Setter private StatusPedido statusPedido;
	
	//Relacionamento com a entidade de Usuario
	@ManyToOne
	@JoinColumn(name = "usuario_id")
    @Getter @Setter private Usuario usuario;

	//Relacionamento com a entidade de Pagamento
	@JsonIgnore
	@OneToMany(mappedBy = "pedidos")
    @Getter private List<Pagamento> pagamentos = new ArrayList<>();

	public Pedido(Long id, Double total, TipoEntrega tipoEntrega, Instant dataOrcamento, Instant dataEntrega,
			Status status, StatusPedido statusPedido, Usuario usuario) {
		super();
		this.id = id;
		this.total = total;
		this.tipoEntrega = tipoEntrega;
		this.dataOrcamento = dataOrcamento;
		this.dataEntrega = dataEntrega;
		this.status = status;
		this.statusPedido = statusPedido;
		this.usuario = usuario;
	}
	
	
}
