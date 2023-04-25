package com.fullstackduck.boxes.entities;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fullstackduck.boxes.entities.pk.ItensOrcamentoPK;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
	@Getter @Setter private Double precoUnit;
	private Double precoTotal;

	public ItensOrcamento(Orcamento orcamento, Produto produto, Integer quantidade, Double precoUnit, Double precoTotal) {
		super();
		id.setOrcamento(orcamento);
		id.setProduto(produto);
		this.quantidade = quantidade;
		this.precoUnit = precoUnit;
		setPrecoTotal(precoUnit);
	}
	
	public Produto getProduto() {
		return id.getProduto();
	}
	
	public void setProduto(Produto produto) {
		id.setProduto(produto);
	}
	
	@JsonIgnore
	public Orcamento getOrcamento() {
		return id.getOrcamento();
	}
	
	public void setOrcamento(Orcamento orcamento) {
		id.setOrcamento(orcamento);
	}
	
	public Double getPrecoTotal() {
		return this.precoTotal;
	}
	
	public void setPrecoTotal(Double precoUnit) {
		Integer quantidade = getQuantidade();
		Double precoTotal =  precoUnit * quantidade;
		this.precoTotal = precoTotal;
	}
}
