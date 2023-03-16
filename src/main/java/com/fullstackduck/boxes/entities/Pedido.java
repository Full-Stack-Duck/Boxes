package com.fullstackduck.boxes.entities;

import java.io.Serializable;
import java.time.Instant;

import com.fullstackduck.boxes.entities.enums.Status;
import com.fullstackduck.boxes.entities.enums.StatusPedido;
import com.fullstackduck.boxes.entities.enums.TipoEntrega;

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
@Table(name="tb_pedido")
@AllArgsConstructor
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
	@Getter @Setter private StatusPedido stausPedido;
}
