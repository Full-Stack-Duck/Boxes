package com.fullstackduck.boxes.entities.pk;

import java.io.Serializable;

import com.fullstackduck.boxes.entities.Cliente;
import com.fullstackduck.boxes.entities.Orcamento;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter @Setter
@EqualsAndHashCode
public class ItensMovimentacaoPK implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@ManyToOne
	@JoinColumn(name = "cliente_id")
	private Cliente cliente;
	
	@ManyToOne
	@JoinColumn(name = "orcamento_id")
	private Orcamento orcamento;
}
