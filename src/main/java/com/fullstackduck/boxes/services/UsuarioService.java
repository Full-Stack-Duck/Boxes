package com.fullstackduck.boxes.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fullstackduck.boxes.entities.Usuario;
import com.fullstackduck.boxes.repositories.UsuarioRepository;
import com.fullstackduck.boxes.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service //Registro de componente
public class UsuarioService {

	@Autowired
	private UsuarioRepository repository;
	@Transactional
	public List<Usuario> findAll(){
		return repository.findAll();
	}
	@Transactional
	public Usuario findById(Long id) {
		Optional<Usuario> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
	}
	@Transactional
	//insere usuario no banco de dados
	public Usuario inserirUsuario(Usuario obj) {
		return repository.save(obj);
	}
	@Transactional
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
	@Transactional
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
	@Transactional
	private void atualizarDados(Usuario entity, Usuario obj) {
		entity.setNome(obj.getNome());
		entity.setDocumento(obj.getDocumento());
		entity.setEmail(obj.getEmail());
		entity.setTelefone(obj.getTelefone());
		entity.setSenha(obj.getSenha());
		entity.setEndereco(obj.getEndereco());
		entity.setLogo(obj.getLogo());
	}
	@Transactional
	private void atualizarStatus(Usuario entity, Usuario obj) {
		entity.setStatus(obj.getStatus());
	}
}

