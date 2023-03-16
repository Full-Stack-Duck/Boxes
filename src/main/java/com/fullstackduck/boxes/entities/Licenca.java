package com.fullstackduck.boxes.entities;

import java.io.Serializable;
import java.time.Instant;

import org.hibernate.annotations.Generated;

import com.fullstackduck.boxes.entities.enums.StatusLicenca;
import com.fullstackduck.boxes.entities.enums.TipoLicenca;

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
@Table(name="tb_licenca")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of="id")
public class Licenca implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//Atributos da classe
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Getter private Long id;
	@Getter @Setter private StatusLicenca statusLicenca;
	@Getter @Setter private Instant dataAquisicao;
	@Getter @Setter private TipoLicenca tipoLicenca;
	@Getter @Setter private Double valor;
	
}
