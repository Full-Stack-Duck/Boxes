package com.fullstackduck.boxes.services;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

import javax.security.auth.login.LoginException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fullstackduck.boxes.entities.Cliente;
import com.fullstackduck.boxes.entities.Produto;
import com.fullstackduck.boxes.entities.Usuario;
import com.fullstackduck.boxes.repositories.PagamentoRepository;
import com.fullstackduck.boxes.repositories.UsuarioRepository;
import com.fullstackduck.boxes.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service //Registro de componente
public class UsuarioService {
	


	@Autowired
	private UsuarioRepository repository;
	
	@Autowired
	private PagamentoRepository repository1;
	
	public List<Usuario> findAll(){
		return repository.findAll();
	}
	
	public Usuario findById(Long id) {
		Optional<Usuario> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
	}
	
	//insere usuario no banco de dados
	public Usuario inserirUsuario(Usuario obj) {
		obj.setDatacadastro(Instant.now());
	    /*
	    // Definir tipo de licença escolhido pelo usuário
	    TipoLicenca tipoLicenca = obj.getTipoLicenca();
	    obj.setTipoLicenca(tipoLicenca);
	    
	    // Definir data de validade da licença baseado no tipo escolhido
	    Instant dataValidadeLicenca = Instant.now();
	    if (tipoLicenca == TipoLicenca.GRATUITA) {
	        dataValidadeLicenca = dataValidadeLicenca.plus(Duration.ofDays(15));
	    } else if (tipoLicenca == TipoLicenca.MENSAL) {
	        dataValidadeLicenca = dataValidadeLicenca.plus(Duration.ofDays(30));
	    } else if (tipoLicenca == TipoLicenca.SEMESTRAL) {
	        dataValidadeLicenca = dataValidadeLicenca.plus(Duration.ofDays(180));
	    } else if (tipoLicenca == TipoLicenca.ANUAL) {
	        dataValidadeLicenca = dataValidadeLicenca.plus(Duration.ofDays(365));
	    }
	    obj.setDataValidadeLicenca(dataValidadeLicenca);
	    */
	    return repository.save(obj);
	}
	
	//atualiza status do usuario no banco de dados
	public Usuario atualizarStatusUsuario(Long id, Usuario obj) {
		try {
			Usuario entity = repository.getReferenceById(id);
			atualizarStatus(entity, obj);
			return repository.save(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}
	
	//atualiza dados do usuario no banco de dados
	public Usuario atualizarUsuario(Long id, Usuario obj) {
		try {
			Usuario entity = repository.getReferenceById(id);
			atualizarDados(entity, obj);
			return repository.save(entity);
		}catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}
	
	private void atualizarDados(Usuario entity, Usuario obj) {
		entity.setNome(obj.getNome());
		entity.setDocumento(obj.getDocumento());
		entity.setEmail(obj.getEmail());
		entity.setTelefone(obj.getTelefone());
		entity.setSenha(obj.getSenha());
		entity.setEndereco(obj.getEndereco());
		entity.setLogo(obj.getLogo());
	}
	
	private void atualizarStatus(Usuario entity, Usuario obj) {
		entity.setStatus(obj.getStatus());
	}
	
	public String recuperarSenha(String email) {
        Usuario usuario = repository.findByEmail(email);
        if (usuario == null) {
            throw new ResourceNotFoundException("Usuário não encontrado para o email: " + email);
        }
        return usuario.getSenha();
    }

    public boolean validarSenha(String email, String senha) {
        Usuario usuario = repository.findByEmail(email);
        if (usuario == null) {
            return false;
        }
        return usuario.getSenha().equals(senha);
    }
    
    public List<Cliente> listarClientes(Long idUsuario) {
        Usuario usuario = findById(idUsuario);
        return usuario.getClientes();
    }
    
    public List<Produto> listarProdutos(Long idUsuario) {
        Usuario usuario = findById(idUsuario);
        return usuario.getProdutos();
    }
    
    public Usuario login(String email, String senha) throws Exception {
        Usuario usuario = repository.findByEmail(email);
        if (usuario == null) {
            throw new LoginException("Usuário não encontrado");
        }

        if (!usuario.getSenha().equals(senha)) {
            throw new LoginException("Senha incorreta");
        }

        return usuario;
    }
    
    
 
}

