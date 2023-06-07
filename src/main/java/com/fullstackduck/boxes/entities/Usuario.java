package com.fullstackduck.boxes.entities;

import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fullstackduck.boxes.entities.enums.Status;
import com.fullstackduck.boxes.entities.enums.StatusLicenca;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//Mapeamento JPA e Lombok
@Entity
@Table(name="tb_usuario")
@NoArgsConstructor
@EqualsAndHashCode(of="id")
public class Usuario implements Serializable,UserDetails {
	private static final long serialVersionUID = 1L;
	
	//Atributos da classe
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Getter @Setter private Long id;
	
	@NotBlank
	@Size(max = 60)
	@Getter @Setter private String nome;
	
	@NotBlank
	@Size(max=18)
	@Getter @Setter private String documento;
	
	@Size(max=11)
	@Getter @Setter private String telefone;
	
	@Getter @Setter private String endereco;
	@Getter @Setter private String logo;
	@Getter @Setter private Instant datacadastro;
	private Integer status;
	
	@NotBlank
	@Email
	@Size(max = 80)
	@Getter @Setter private String email;
	
	@NotBlank
	/*@Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$", message = "A senha deve ter pelo menos 8 caracteres, uma letra e um número")*/
	@Getter @Setter private String senha;
	
	//Relacionamento com a entidade de Licencas
	@JsonIgnore
	@OneToMany(mappedBy = "usuario", fetch = FetchType.EAGER)
    @Getter private List<Licenca> licencas = new ArrayList<>();

	//Relacionamento com a entidade de Clientes
	@JsonIgnore
	@OneToMany(mappedBy = "usuario", fetch = FetchType.EAGER)
    @Getter private List<Cliente> clientes = new ArrayList<>();
	
	//Relacionamento com a entidade de Produtos
	@JsonIgnore
	@OneToMany(mappedBy = "usuario", fetch = FetchType.EAGER)
    @Getter private List<Produto> produtos = new ArrayList<>();

	//Relacionamento com a entidade de Despesas
	@JsonIgnore
	@OneToMany(mappedBy = "usuario", fetch = FetchType.EAGER)
    @Getter private List<Despesa> despesas = new ArrayList<>();
	
	//Relacionamento com a entidade de Receitas
	@JsonIgnore
	@OneToMany(mappedBy = "usuario", fetch = FetchType.EAGER)
    @Getter private List<Receita> receitas = new ArrayList<>();

	//Relacionamento com a entidade de Orcamentos
	@JsonIgnore
	@OneToMany(mappedBy = "usuario", fetch = FetchType.EAGER)
    @Getter private List<Orcamento> orcamentos = new ArrayList<>();
	
	//Relacionamento com a entidade de Pedidos
	@JsonIgnore
	@OneToMany(mappedBy = "usuario", fetch = FetchType.EAGER)
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
	
	public Licenca findLicenca() {
		List<Licenca> licencas = getLicencas();
		Licenca atual = null;
		for (Licenca i: licencas) {
			if (i.getStatusLicenca() == StatusLicenca.ATIVA) {
				atual = i;
			}
		}
		return atual;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return List.of(new SimpleGrantedAuthority("ROLE_USER"));
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
