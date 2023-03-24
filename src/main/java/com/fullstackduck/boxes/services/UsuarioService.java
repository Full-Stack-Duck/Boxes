package com.fullstackduck.boxes.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fullstackduck.boxes.entities.Usuario;
import com.fullstackduck.boxes.entities.enums.Status;
import com.fullstackduck.boxes.repositories.UsuarioRepository;

@Service //Registro de componente
public class UsuarioService {

	@Autowired
	private UsuarioRepository repository;
	
	public List<Usuario> findAll(){
		return repository.findAll();
	}
	
	public Usuario findById(Long id) {
		Optional<Usuario> obj = repository.findById(id);
		return obj.get();
	}
	
	public Usuario criarUsuario(Usuario usuario) {
        // Verifica se o email já está sendo usado
        if (repository.existsByEmail(usuario.getEmail())) {
            throw new IllegalArgumentException("Email já está em uso.");
        }
        // Cria o usuário
        
        usuario.setStatus(Status.ATIVO);
        return repository.save(usuario);
    }

    public Usuario alterarUsuario(Long id, Usuario usuario) {
        Optional<Usuario> optional = repository.findById(id);
        if (optional.isEmpty()) {
            throw new IllegalArgumentException("Usuário não encontrado.");
        }
        Usuario usuarioExistente = optional.get();
        // Verifica se o email já está sendo usado por outro usuário
        if (repository.existsByEmailAndIdNot(usuario.getEmail(), id)) {
            throw new IllegalArgumentException("Email já está em uso por outro usuário.");
        }
        // Atualiza os campos do usuário existente
        usuarioExistente.setNome(usuario.getNome());
        usuarioExistente.setDocumento(usuario.getDocumento());
        usuarioExistente.setEmail(usuario.getEmail());
        usuarioExistente.setTelefone(usuario.getTelefone());
        usuarioExistente.setEndereco(usuario.getEndereco());
        usuarioExistente.setLogo(usuario.getLogo());
        usuarioExistente.setStatus(usuario.getStatus());
        return repository.save(usuarioExistente);
    }

    public Usuario desativarUsuario(Long id) {
        Optional<Usuario> optional = repository.findById(id);
        if (optional.isEmpty()) {
            throw new IllegalArgumentException("Usuário não encontrado.");
        }
        Usuario usuarioExistente = optional.get();
        usuarioExistente.setStatus(Status.INATIVO);
        return repository.save(usuarioExistente);
    }

}

