package com.fullstackduck.boxes.entities;

import java.io.Serializable;

import com.fullstackduck.boxes.entities.pk.ItensMovimentacaoPK;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//Mapeamento JPA e Lombok
@Entity
@Table(name="tb_endereco_entrega")
@NoArgsConstructor
@EqualsAndHashCode(of="id")
public class ItensMovimentacao implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ItensMovimentacaoPK id;
	
	@Getter @Setter private String descricao;

	public ItensMovimentacao(Cliente cliente, Orcamento orcamento, String descricao) {
		super();
		id.setCliente(cliente);
		id.setOrcamento(orcamento);
		this.descricao = descricao;
	}
	
	public Cliente getCliente() {
		return id.getCliente();
	}
	
	public void setCliente(Cliente cliente) {
		id.setCliente(cliente);
	}
	
	public Orcamento getOrcamento() {
		return id.getOrcamento();
	}
	
	public void setOrcamento(Orcamento orcamento) {
		id.setOrcamento(orcamento);
	}
}
