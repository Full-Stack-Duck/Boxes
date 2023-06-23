package com.fullstackduck.boxes.services;

import java.text.Normalizer;
import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fullstackduck.boxes.entities.Cliente;
import com.fullstackduck.boxes.entities.Usuario;
import com.fullstackduck.boxes.entities.enums.Status;
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
		obj.setDataCliente(Instant.now());
		obj.setDataNascimento(null);
		obj.setDocumento(null);
		obj.setStatus(Status.ATIVO);
	    // Extrai o ID do usuário do token JWT
	    Long userId = getUserIdFromToken();
	    
	    // Busca o usuário pelo ID
	    Usuario user = usuarioRepository.findById(userId).orElse(null);

        if (user != null) {
            // Associa o produto ao usuário
            obj.setUsuario(user);
            // Salva o produto no banco de dados
        }
        return clienteRepository.save(obj);
	}
	
	//atualiza status do cliente no banco de dados
	
	public Cliente atualizarStatusCliente(Long id, Cliente obj) {
		try {
			Cliente entity = clienteRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado com o id: " + id));
			atualizarStatusCliente(entity, obj);
			return clienteRepository.save(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}
	
	//atualiza dados do cliente no banco de dados
	
	public Cliente atualizarCliente(Long id, Cliente obj) {
		try {
			Cliente entity = clienteRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado com o id: " + id));
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
		entity.setStatus(obj.getStatus());
	}
	
	@Transactional
	public List<Cliente> listarClientes(Long idUsuario) {
        Usuario usuario = usuarioRepository.findById(idUsuario).orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado com o id: " + idUsuario));
        return usuario.getClientes();
    }
	
	@Transactional
	public List<Cliente> listarClientePeriodo(String dataInicio, String dataFim) {
		DateTimeFormatter formatter = DateTimeFormatter.ISO_INSTANT;
		Instant data1 = Instant.from(formatter.parse(dataInicio));
		Instant data2 = Instant.from(formatter.parse(dataFim));
	    return clienteRepository.findByDataClienteBetween(data1, data2);
	}
	
	public List<Cliente> buscarClientesPorNome(String nome, Long id) {
	    Usuario obj = usuarioRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado com o id: " + id));
	    List<Cliente> client = new ArrayList<>();
	    String normalizedNome = Normalizer.normalize(nome, Normalizer.Form.NFD);
	    String patternString = "(?i).*" + normalizedNome.replaceAll("\\p{M}", "") + ".*";
	    Pattern pattern = Pattern.compile(patternString);
	    for (Cliente cliente : obj.getClientes()) {
	        String normalizedClienteNome = Normalizer.normalize(cliente.getNome(), Normalizer.Form.NFD);
	        Matcher matcher = pattern.matcher(normalizedClienteNome.replaceAll("\\p{M}", ""));
	        if (matcher.matches()) {
	            client.add(cliente);
	        }
	    }
	    return client;
	}
	
	private Long getUserIdFromToken() {
	    // Obtém o ID do usuário do token JWT
	    Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

	    if (principal instanceof UserDetails) {
	        return ((Usuario) principal).getId();
	    } else {
	        return Long.parseLong(principal.toString());
	    }
	}
}
