package com.fullstackduck.boxes.entities;

import java.io.Serializable;
import java.util.List;

import com.fullstackduck.boxes.entities.enums.Status;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//Mapeamento JPA e Lombok
@Entity
@Table(name="tb_usuario")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of="id")
public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//Atributos da classe
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Getter private Long id;
	@Getter @Setter private String nome;
	@Getter @Setter private String documento;
	@Getter @Setter private String email;
	@Getter @Setter private String telefone;
	@Getter @Setter private String senha;
	@Getter @Setter private String endereco;
	@Getter @Setter private String logo;
	@Getter @Setter private Status status;
	
	//Relacionamento com a entidade de Licenca
    @OneToMany(mappedBy = "usuario")
    @Getter @Setter private List<Licenca> licencas;
	
}
