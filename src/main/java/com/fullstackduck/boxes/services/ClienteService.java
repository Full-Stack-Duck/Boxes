package com.fullstackduck.boxes.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fullstackduck.boxes.entities.Cliente;
import com.fullstackduck.boxes.repositories.ClienteRepository;
import com.fullstackduck.boxes.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service //Registro de componente
public class ClienteService {

	@Autowired
	private ClienteRepository repository;
	
	public List<Cliente> findAll(){
		return repository.findAll();
	}
	
	public Cliente findById(Long id) {
		Optional<Cliente> obj = repository.findById(id);
		return obj.get();
	}

	//insere cliente no banco de dados
	public Cliente inserirCliente(Cliente obj) {
		return repository.save(obj);
	}
	
	//atualiza status do cliente no banco de dados
	public Cliente atualizarStatusCliente(Long id, Cliente obj) {
		try {
			Cliente entity = repository.getReferenceById(id);
			atualizarStatusCliente(entity, obj);
			return repository.save(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}
	
	//atualiza dados do cliente no banco de dados
	public Cliente atualizarCliente(Long id, Cliente obj) {
		try {
			Cliente entity = repository.getReferenceById(id);
			atualizarDadosCliente(entity, obj);
			return repository.save(entity);
		}catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}
	
	private void atualizarDadosCliente(Cliente entity, Cliente obj) {
		entity.setNome(obj.getNome());
		entity.setEmail(obj.getEmail());
		entity.setTelefone(obj.getTelefone());
		entity.setDataNascimento(obj.getDataNascimento());
		entity.setDocumento(obj.getDocumento());
	}
	
	private void atualizarStatusCliente(Cliente entity, Cliente obj) {
		entity.setStatusCliente(obj.getStatusCliente());
	}
}
