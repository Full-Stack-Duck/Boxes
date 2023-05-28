package com.fullstackduck.boxes.services;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

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

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository repository;
	
	@Async
    public CompletableFuture<List<Usuario>> findAll() {
        List<Usuario> usuarios = repository.findAll();
        return CompletableFuture.completedFuture(usuarios);
    }
	
	@Async
	public CompletableFuture<Usuario> findById(Long id) {
		Optional<Usuario> obj = repository.findById(id);
		return CompletableFuture.completedFuture(obj.orElseThrow(() -> new ResourceNotFoundException(id)));
	}
	
	@Async
	public CompletableFuture<Usuario> inserirUsuario(Usuario obj) {
		obj.setDatacadastro(Instant.now());
		Usuario usuario = repository.save(obj);
		return CompletableFuture.completedFuture(usuario);
	}
	
	@Async
	public CompletableFuture<Usuario> atualizarStatusUsuario(Long id, Usuario obj) {
		try {
			Usuario entity = repository.getReferenceById(id);
			atualizarStatus(entity, obj);
			Usuario usuario = repository.save(entity);
			return CompletableFuture.completedFuture(usuario);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}
	
	@Async
	public CompletableFuture<Usuario> atualizarUsuario(Long id, Usuario obj) {
		try {
			Usuario entity = repository.getReferenceById(id);
			atualizarDados(entity, obj);
			Usuario usuario = repository.save(entity);
			return CompletableFuture.completedFuture(usuario);
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
	public CompletableFuture<String> recuperarSenha(String email) {
		Usuario usuario = repository.findByEmail(email);
		if (usuario == null) {
			throw new ResourceNotFoundException("Usuário não encontrado para o email: " + email);
		}
		return CompletableFuture.completedFuture(usuario.getSenha());
	}

	@Async
	public CompletableFuture<Boolean> validarSenha(String email, String senha) {
		Usuario usuario = repository.findByEmail(email);
		if (usuario == null) {
			return CompletableFuture.completedFuture(false);
		}
		return CompletableFuture.completedFuture(usuario.getSenha().equals(senha));
	}
    
	@Async
	public CompletableFuture<List<Cliente>> listarClientes(Long idUsuario) {
		Usuario usuario = findById(idUsuario).join();
		return CompletableFuture.completedFuture(usuario.getClientes());
	}
    
	@Async
	public CompletableFuture<List<Orcamento>> listarOrcamentos(Long idUsuario) {
		Usuario usuario = repository.getReferenceById(idUsuario);
		return CompletableFuture.completedFuture(usuario.getOrcamentos());
	}
    
	@Async
	public CompletableFuture<List<Produto>> listarProdutos(Long idUsuario) {
		Usuario usuario = findById(idUsuario).join();
		return CompletableFuture.completedFuture(usuario.getProdutos());
	}
    
	@Async
	public CompletableFuture<List<Receita>> listarReceitas(Long idUsuario){
		Usuario usuario = repository.getReferenceById(idUsuario);
		return CompletableFuture.completedFuture(usuario.getReceitas());
	}
    
	@Async
	public CompletableFuture<Usuario> login(String email, String senha) throws LoginException {
		Usuario usuario = repository.findByEmail(email);
		if (usuario == null) {
			throw new LoginException("Usuário não encontrado");
		}

		if (!usuario.getSenha().equals(senha)) {
			throw new LoginException("Senha incorreta");
		}

		return CompletableFuture.completedFuture(usuario);
	}
}
