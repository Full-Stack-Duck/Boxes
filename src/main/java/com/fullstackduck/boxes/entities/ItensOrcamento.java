package com.fullstackduck.boxes.entities;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fullstackduck.boxes.entities.pk.ItensOrcamentoPK;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//Mapeamento JPA e Lombok
@Entity
@Table(name="tb_item_orcamento")
@NoArgsConstructor
@EqualsAndHashCode(of="id")
public class ItensOrcamento implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ItensOrcamentoPK id = new ItensOrcamentoPK();
	
	@Getter @Setter private Integer quantidade;
	@Getter @Setter private Double preco;

	public ItensOrcamento(Orcamento orcamento, Produto produto, Integer quantidade, Double preco) {
		super();
		id.setOrcamento(orcamento);
		id.setProduto(produto);
		this.quantidade = quantidade;
		this.preco = preco;
	}
	
	public Produto getProduto() {
		return id.getProduto();
	}
	
	public void setProduto(Produto cliente) {
		id.setProduto(cliente);
	}
	
	@JsonIgnore
	public Orcamento getOrcamento() {
		return id.getOrcamento();
	}
	
	public void setOrcamento(Orcamento orcamento) {
		id.setOrcamento(orcamento);
	}
}
