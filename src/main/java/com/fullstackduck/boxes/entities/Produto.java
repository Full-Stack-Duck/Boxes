package com.fullstackduck.boxes.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fullstackduck.boxes.entities.enums.Status;
import com.fullstackduck.boxes.entities.enums.TipoArmazenamento;
import com.fullstackduck.boxes.entities.enums.TipoProduto;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//Mapeamento JPA e Lombok
@Entity
@Table(name="tb_produto")
@NoArgsConstructor
@EqualsAndHashCode(of="id")
public class Produto implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//Atributos da classe
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Getter @Setter private Long id;
	
	@NotBlank
	@Getter @Setter private String nome;
	
	@Getter @Setter private Double valor;
	
	
	private Integer categoria;
	@Getter @Setter private Integer quantidade;
	private Integer tipo;
	private Integer status;
	@Getter @Setter private String observacao;
	
	//Relacionamento com a entidade de Usuario
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "usuario_id")
    @Getter @Setter private Usuario usuario;
	
	//Relacionamento com a entidade de MovimentacaoEstoque
	@OneToMany(mappedBy = "produto", fetch = FetchType.EAGER)
	private List<MovimentacaoEstoque> movimentacoes;
	
	//Relacionamento com a entidade de ItensOrcamento
	@OneToMany(mappedBy = "id.produto", fetch = FetchType.EAGER)
	private Set<ItensOrcamento> items = new HashSet<>();

	public Produto(Long id, String nome, Double valor, TipoArmazenamento categoria, Integer quantidade,
			TipoProduto tipo, Status status, String observacao, Usuario usuario) {
		super();
		this.id = id;
		this.nome = nome;
		this.valor = valor;
		setCategoria(categoria);
		this.quantidade = quantidade;
		setTipo(tipo);
		setStatus(status);
		this.observacao = observacao;
		this.usuario = usuario;
	}

	public TipoArmazenamento getCategoria() {
		return TipoArmazenamento.valueOf(categoria);
		}

	public void setCategoria(TipoArmazenamento categoria) {
		if(categoria != null) {
		this.categoria = categoria.getCode();
		}
	}

	public TipoProduto getTipo() {
		return TipoProduto.valueOf(tipo);
	}

	public void setTipo(TipoProduto tipo) {
		if(tipo != null) {
		this.tipo = tipo.getCode();
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

	public Produto(long l, String string, String string2, double d) {
		// TODO Auto-generated constructor stub
	}

}