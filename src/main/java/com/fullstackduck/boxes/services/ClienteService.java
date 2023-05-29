package com.fullstackduck.boxes.services;

import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fullstackduck.boxes.entities.Cliente;
import com.fullstackduck.boxes.entities.Orcamento;
import com.fullstackduck.boxes.entities.Usuario;
import com.fullstackduck.boxes.repositories.ClienteRepository;
import com.fullstackduck.boxes.repositories.UsuarioRepository;
import com.fullstackduck.boxes.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service //Registro de componente
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	

	public List<Cliente> findAll(){
		return clienteRepository.findAll();
	}
	
	public Cliente findById(Long id) {
		Optional<Cliente> obj = clienteRepository.findById(id);
		return obj.get();
	}

	//insere cliente no banco de dados
	
	public Cliente inserirCliente(Cliente obj) {
		return clienteRepository.save(obj);
	}
	
	//atualiza status do cliente no banco de dados
	
	public Cliente atualizarStatusCliente(Long id, Cliente obj) {
		try {
			Cliente entity = clienteRepository.getReferenceById(id);
			atualizarStatusCliente(entity, obj);
			return clienteRepository.save(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}
	
	//atualiza dados do cliente no banco de dados
	
	public Cliente atualizarCliente(Long id, Cliente obj) {
		try {
			Cliente entity = clienteRepository.getReferenceById(id);
			atualizarDadosCliente(entity, obj);
			return clienteRepository.save(entity);
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
	
	@Transactional
	public List<Cliente> listarClientes(Long idUsuario) {
        Usuario usuario = usuarioRepository.getReferenceById(idUsuario);
        return usuario.getClientes();
    }
	
	@Transactional
	public List<Cliente> listarClientePeriodo(String dataInicio, String dataFim) {
		DateTimeFormatter formatter = DateTimeFormatter.ISO_INSTANT;
		Instant data1 = Instant.from(formatter.parse(dataInicio));
		Instant data2 = Instant.from(formatter.parse(dataFim));
	    return clienteRepository.findByDataClienteBetween(data1, data2);
	}
}
