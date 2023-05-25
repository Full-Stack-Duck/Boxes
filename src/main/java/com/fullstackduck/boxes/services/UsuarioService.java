package com.fullstackduck.boxes.services;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

import javax.security.auth.login.LoginException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.fullstackduck.boxes.entities.Cliente;
import com.fullstackduck.boxes.entities.Orcamento;
import com.fullstackduck.boxes.entities.Produto;
import com.fullstackduck.boxes.entities.Receita;
import com.fullstackduck.boxes.entities.Usuario;
import com.fullstackduck.boxes.repositories.UsuarioRepository;
import com.fullstackduck.boxes.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service //Registro de componente
public class UsuarioService {
	


	@Autowired
	private UsuarioRepository repository;
	
	@Async
	public List<Usuario> findAll(){
		return repository.findAll();
	}
	
	@Async
	public Usuario findById(Long id) {
		Optional<Usuario> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
	}
	
	//insere usuario no banco de dados
	@Async
	public Usuario inserirUsuario(Usuario obj) {
		obj.setDatacadastro(Instant.now());
	    return repository.save(obj);
	}
	
	//atualiza status do usuario no banco de dados
	@Async
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
	@Async
	public Usuario atualizarUsuario(Long id, Usuario obj) {
		try {
			Usuario entity = repository.getReferenceById(id);
			atualizarDados(entity, obj);
			return repository.save(entity);
		}catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}
	
	@Async
	private void atualizarDados(Usuario entity, Usuario obj) {
		entity.setNome(obj.getNome());
		entity.setDocumento(obj.getDocumento());
		entity.setEmail(obj.getEmail());
		entity.setTelefone(obj.getTelefone());
		entity.setSenha(obj.getSenha());
		entity.setEndereco(obj.getEndereco());
		entity.setLogo(obj.getLogo());
	}
	
	@Async
	private void atualizarStatus(Usuario entity, Usuario obj) {
		entity.setStatus(obj.getStatus());
	}
	
	@Async
	public String recuperarSenha(String email) {
        Usuario usuario = repository.findByEmail(email);
        if (usuario == null) {
            throw new ResourceNotFoundException("Usuário não encontrado para o email: " + email);
        }
        return usuario.getSenha();
    }

	@Async
    public boolean validarSenha(String email, String senha) {
        Usuario usuario = repository.findByEmail(email);
        if (usuario == null) {
            return false;
        }
        return usuario.getSenha().equals(senha);
    }
    
	@Async
    public List<Cliente> listarClientes(Long idUsuario) {
        Usuario usuario = findById(idUsuario);
        return usuario.getClientes();
    }
    
	@Async
    public List<Orcamento> listarOrcamentos(Long idUsuario) {
        Usuario usuario = repository.getReferenceById(idUsuario);
        return usuario.getOrcamentos();
    }
    
	@Async
    public List<Produto> listarProdutos(Long idUsuario) {
        Usuario usuario = findById(idUsuario);
        return usuario.getProdutos();
    }
    
	@Async
    public List<Receita> listarReceitas(Long idUsuario){
    	Usuario usuario = repository.getReferenceById(idUsuario);
    	return usuario.getReceitas();
    }
    
	@Async
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

