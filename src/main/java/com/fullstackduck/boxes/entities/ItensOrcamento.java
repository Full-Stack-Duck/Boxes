package com.fullstackduck.boxes.entities;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fullstackduck.boxes.entities.pk.ItensOrcamentoPK;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
	
	@Getter private Double precoUnit;
	
	private Double precoTotal;
	
	
	@Getter @Setter private Double desconto = 0.0;

	public ItensOrcamento(Orcamento orcamento, Produto produto, Integer quantidade, Double precoUnit, Double desconto, Double setPrecoTotal) {
		super();
		id.setOrcamento(orcamento);
		id.setProduto(produto);
		this.quantidade = quantidade;
		setPrecoUnit(precoUnit);
		this.desconto = desconto;
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
	
	public void setPrecoUnit(Double precoUnit) {
		this.precoUnit = getProduto().getValor() - this.desconto;
	}
	
	public Double getPrecoTotal() {
		return this.precoTotal;
	}
	
	public void setPrecoTotal(Double precoUnit) {
		if (desconto >= 0 && desconto <= precoUnit) {
			Integer quantidade = getQuantidade();
			Double precoTotal =  (precoUnit - this.desconto) * quantidade;
			this.precoTotal = precoTotal;
		} else {
			//lançar exceção de desconto inválido
		}
	}
}
