package com.fullstackduck.boxes.services;

import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fullstackduck.boxes.entities.Cliente;
import com.fullstackduck.boxes.entities.Usuario;
import com.fullstackduck.boxes.repositories.ClienteRepository;
import com.fullstackduck.boxes.repositories.UsuarioRepository;
import com.fullstackduck.boxes.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Async
	public CompletableFuture<List<Cliente>> findAll(){
		List<Cliente> clientes = clienteRepository.findAll();
		return CompletableFuture.completedFuture(clientes);
	}
	
	@Async
	public CompletableFuture<Cliente> findById(Long id) {
		Optional<Cliente> obj = clienteRepository.findById(id);
		return CompletableFuture.completedFuture(obj.get());
	}

	@Async
	public CompletableFuture<Cliente> inserirCliente(Cliente obj) {
		Cliente cliente = clienteRepository.save(obj);
		return CompletableFuture.completedFuture(cliente);
	}
	
	@Async
	public CompletableFuture<Cliente> atualizarStatusCliente(Long id, Cliente obj) {
		try {
			Cliente entity = clienteRepository.getReferenceById(id);
			atualizarStatusCliente(entity, obj);
			Cliente cliente = clienteRepository.save(entity);
			return CompletableFuture.completedFuture(cliente);
			} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}
	
	@Async
	public CompletableFuture<Cliente> atualizarCliente(Long id, Cliente obj) {
		try {
			Cliente entity = clienteRepository.getReferenceById(id);
			atualizarDadosCliente(entity, obj);
			Cliente cliente = clienteRepository.save(entity);
			return CompletableFuture.completedFuture(cliente);
		}catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}
	
	@Async
	private void atualizarDadosCliente(Cliente entity, Cliente obj) {
		entity.setNome(obj.getNome());
		entity.setEmail(obj.getEmail());
		entity.setTelefone(obj.getTelefone());
		entity.setDataNascimento(obj.getDataNascimento());
		entity.setDocumento(obj.getDocumento());
	}
	
	@Async
	private void atualizarStatusCliente(Cliente entity, Cliente obj) {
		entity.setStatusCliente(obj.getStatusCliente());
	}
	
	@Transactional
	@Async
	public CompletableFuture<List<Cliente>> listarClientes(Long idUsuario) {
        Usuario usuario = usuarioRepository.getReferenceById(idUsuario);
        List<Cliente> clientes = usuario.getClientes();
        return CompletableFuture.completedFuture(clientes);
    }
	
	@Transactional
	@Async
	public CompletableFuture<List<Cliente>> listarClientePeriodo(String dataInicio, String dataFim) {
		DateTimeFormatter formatter = DateTimeFormatter.ISO_INSTANT;
		Instant data1 = Instant.from(formatter.parse(dataInicio));
		Instant data2 = Instant.from(formatter.parse(dataFim));
	    List<Cliente> clientes = clienteRepository.findByDataClienteBetween(data1, data2);
	    return CompletableFuture.completedFuture(clientes);
	}
}
