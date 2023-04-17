package com.fullstackduck.boxes.entities;

import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fullstackduck.boxes.entities.enums.Status;
import com.fullstackduck.boxes.entities.enums.TipoLicenca;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//Mapeamento JPA e Lombok
@Entity
@Table(name="tb_usuario")
@NoArgsConstructor
@EqualsAndHashCode(of="id")
public class Usuario implements UserDetails, Serializable {
	private static final long serialVersionUID = 1L;
	
	//Atributos da classe
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Getter @Setter private Long id;
	@Getter @Setter private String nome;
	@Getter @Setter private String documento;
	
	
	
	@Getter @Setter private String telefone;

	@Getter @Setter private String endereco;
	@Getter @Setter private String logo;
	@Getter @Setter private Instant datacadastro;
	private Integer status;
	
<<<<<<< Updated upstream
	@Column(unique = true)
	@Getter @Setter private String email;
	@Getter @Setter private String senha;
	
	@Enumerated(EnumType.STRING)
    private TipoLicenca tipoLicenca;
    
	@Getter @Setter private Instant dataValidadeLicenca;
	
=======
>>>>>>> Stashed changes
	//Relacionamento com a entidade de Licencas
	@JsonIgnore
	@OneToMany(mappedBy = "usuario")
    @Getter private List<Licenca> licencas = new ArrayList<>();

	//Relacionamento com a entidade de Clientes
	@JsonIgnore
	@OneToMany(mappedBy = "usuario")
    @Getter private List<Cliente> clientes = new ArrayList<>();
	
	//Relacionamento com a entidade de Produtos
	@JsonIgnore
	@OneToMany(mappedBy = "usuario")
    @Getter private List<Produto> produtos = new ArrayList<>();

	//Relacionamento com a entidade de Despesas
	@JsonIgnore
	@OneToMany(mappedBy = "usuario")
    @Getter private List<Despesa> despesas = new ArrayList<>();
	
	//Relacionamento com a entidade de Estoque
	@JsonIgnore
	@OneToOne(mappedBy = "usuario")
	@Getter @Setter private Estoque estoque;
	
	//Relacionamento com a entidade de Receitas
	@JsonIgnore
	@OneToMany(mappedBy = "usuario")
    @Getter private List<Receita> receitas = new ArrayList<>();

	//Relacionamento com a entidade de Orcamentos
	@JsonIgnore
	@OneToMany(mappedBy = "usuario")
    @Getter private List<Orcamento> orcamentos = new ArrayList<>();
	
	//Relacionamento com a entidade de Pedidos
	@JsonIgnore
	@OneToMany(mappedBy = "usuario")
    @Getter private List<Pedido> pedidos = new ArrayList<>();
	

	public Usuario(Long id, String nome, String documento, String email, String telefone, String senha, String endereco,
			String logo,Instant datacadastro, Status status) {
		super();
		this.id = id;
		this.nome = nome;
		this.documento = documento;
		this.email = email;
		this.telefone = telefone;
		this.senha = senha;
		this.endereco = endereco;
		this.logo = logo;
		this.datacadastro = datacadastro;
		setStatus(status);
	}
	
	public Status getStatus() {
		return Status.valueOf(status);
	}

	public void setStatus(Status status) {
		if(status != null) {
			this.status = status.getCode();
		}
	}

	public void setTipoLicenca(TipoLicenca tipoLicenca2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return licencas.stream().map(role -> new SimpleGrantedAuthority(role.getAuthority()))
				.collect(Collectors.toList());
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return senha;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return email;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

	
}
