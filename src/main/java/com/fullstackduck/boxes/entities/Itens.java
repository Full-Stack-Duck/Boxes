package com.fullstackduck.boxes.entities;

import java.io.Serializable;

import com.fullstackduck.boxes.entities.pk.ItensPK;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//Mapeamento JPA e Lombok
@Entity
@Table(name="tb_itens")
@NoArgsConstructor
@EqualsAndHashCode(of="id")
public class Itens implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ItensPK id;
	
	@Getter @Setter private Integer quantidade;
	@Getter @Setter private Double preco;

	public Itens(Orcamento orcamento, Produto produto, Integer quantidade, Double preco) {
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
	
	public Orcamento getOrcamento() {
		return id.getOrcamento();
	}
	
	public void setOrcamento(Orcamento orcamento) {
		id.setOrcamento(orcamento);
	}
}
