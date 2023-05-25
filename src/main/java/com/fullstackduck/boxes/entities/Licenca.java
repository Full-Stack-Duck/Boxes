package com.fullstackduck.boxes.entities;

import java.io.Serializable;
import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

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
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
	private Instant dataValidade = Instant.now();
	
	private Integer diasLicenca;
	private Integer tipoLicenca;
	
	@Getter private Double valor;
	
	//Relacionamento com a entidade de Usuario
	@ManyToOne
	@JoinColumn(name = "usuario_id")
    @Getter @Setter private Usuario usuario;


	public Licenca(Long id, StatusLicenca statusLicenca, Instant dataAquisicao, Instant dataValidade, Integer diasLicenca, TipoLicenca tipoLicenca, Double valor,
			Usuario usuario) {
		super();
		this.id = id;
		setStatusLicenca(statusLicenca);
		this.dataAquisicao = dataAquisicao;
		setTipoLicenca(tipoLicenca);
		setDataValidade(dataAquisicao);
		calcularDiasLicenca();
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

	public void setDataValidade(Instant dataAquisicao) {
		Instant dataValidade = getDataAquisicao();
		TipoLicenca tipoLicenca = getTipoLicenca();
		if (tipoLicenca == TipoLicenca.GRATUITA) {
    		dataValidade = dataValidade.plus(Duration.ofDays(30));
    	} else if (tipoLicenca == TipoLicenca.MENSAL) {
            dataValidade = dataValidade.plus(Duration.ofDays(30));
        } else if (tipoLicenca == TipoLicenca.SEMESTRAL) {
            dataValidade = dataValidade.plus(Duration.ofDays(180));
        } else if (tipoLicenca == TipoLicenca.ANUAL) {
            dataValidade = dataValidade.plus(Duration.ofDays(365));
        }
	    this.dataValidade = dataValidade;
	}

	public Instant getDataValidade() {
	    return this.dataValidade;
	}

	public void calcularDiasLicenca() {
	    // Verifica se a data de validade já passou
	    Instant dataAtual = Instant.now();
	    Instant dataValidade = getDataValidade();
	    if (dataAtual.isAfter(dataValidade)) {
	        this.diasLicenca = 0;
	    }
	    // Calcula a diferença em dias entre as duas datas
	    long dias = ChronoUnit.DAYS.between(dataAtual, dataValidade);
	    this.diasLicenca = (int) dias;
	}
	
	public Integer getDiasLicenca() {
		calcularDiasLicenca();
	    return this.diasLicenca;
	}
	
	public void setValor() {
		Double valor = 0.0;
		TipoLicenca tipoLicenca = getTipoLicenca();
		if (tipoLicenca == TipoLicenca.GRATUITA) {
    		valor = 0.0;
    	} else if (tipoLicenca == TipoLicenca.MENSAL) {
    		valor = 91.0;
        } else if (tipoLicenca == TipoLicenca.SEMESTRAL) {
        	valor = 516.0;
        } else if (tipoLicenca == TipoLicenca.ANUAL) {
        	valor = 984.0;
        }
		this.valor = valor;
	}
}
