package com.fullstackduck.boxes.entities;

import java.io.Serializable;
import java.time.Instant;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fullstackduck.boxes.entities.enums.StatusLicenca;
import com.fullstackduck.boxes.entities.enums.TipoLicenca;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//Mapeamento JPA e Lombok
@Entity
@Table(name="tb_licenca")
@NoArgsConstructor
@EqualsAndHashCode(of="id")
public class Licenca implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//Atributos da classe
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Getter private Long id;
	private Integer statusLicenca;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
	@Getter @Setter private Instant dataAquisicao;
	private Integer tipoLicenca;
	@Getter @Setter private Double valor;
	
	
	//Relacionamento com a entidade de Usuario
	@ManyToOne
	@JoinColumn(name = "usuario_id")
    @Getter @Setter private Usuario usuario;


	public Licenca(Long id, StatusLicenca statusLicenca, Instant dataAquisicao, TipoLicenca tipoLicenca, Double valor,
			Usuario usuario) {
		super();
		this.id = id;
		setStatusLicenca(statusLicenca);
		this.dataAquisicao = dataAquisicao;
		setTipoLicenca(tipoLicenca);
		this.valor = valor;
		this.usuario = usuario;
	}


	public StatusLicenca getStatusLicenca() {
		return StatusLicenca.valueOf(statusLicenca);
	}


	public void setStatusLicenca(StatusLicenca statusLicenca) {
		if(statusLicenca != null) {
		this.statusLicenca = statusLicenca.getCode();
		}
	}


	public TipoLicenca getTipoLicenca() {
		return TipoLicenca.valueOf(tipoLicenca);
	}


	public void setTipoLicenca(TipoLicenca tipoLicenca) {
		if(tipoLicenca != null) {
		this.tipoLicenca = tipoLicenca.getCode();
		}
	}
}
