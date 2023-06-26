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

import com.fullstackduck.boxes.entities.Despesa;
import com.fullstackduck.boxes.entities.Pedido;
import com.fullstackduck.boxes.entities.Usuario;
import com.fullstackduck.boxes.entities.enums.Categoria;
import com.fullstackduck.boxes.entities.enums.Status;
import com.fullstackduck.boxes.repositories.DespesaRepository;
import com.fullstackduck.boxes.repositories.UsuarioRepository;
import com.fullstackduck.boxes.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service //Registro de componente
public class DespesaService {

	@Autowired
	private DespesaRepository despesaRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	
	public List<Despesa> findAll(){
		return despesaRepository.findAll();
	}
	
	
	public Despesa findById(Long id) {
		Optional<Despesa> obj = despesaRepository.findById(id);
		return obj.get();
	}

	//insere despesa no banco de dados
	
	public Despesa inserirDespesa(Despesa obj) {
		// Extrai o ID do usuário do token JWT
	    Long userId = getUserIdFromToken();
	    
	    // Busca o usuário pelo ID
	    Usuario user = usuarioRepository.findById(userId).orElse(null);

        if (user != null) {
            // Associa o produto ao usuário
            obj.setUsuario(user);
            // Salva o produto no banco de dados
        }
        obj.setStatus(Status.ATIVO);
		return despesaRepository.save(obj);
	}
	
	//atualiza dados da despesa no banco de dados
	
	public Despesa atualizarDespesa(Long id, Despesa obj) {
		try {
			Despesa entity = despesaRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Despesa não encontrada com o id: " + id));
			atualizarDadosDespesa(entity, obj);
			return despesaRepository.save(entity);
		}catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}
	
	private void atualizarDadosDespesa(Despesa entity, Despesa obj) {
		entity.setNome(obj.getNome());
		entity.setCategoria(obj.getCategoria());
		entity.setValor(obj.getValor());
		entity.setObservacao(obj.getObservacao());
		entity.setDataDespesa(obj.getDataDespesa());
	}
	
	
	public void excluirDespesa(Long id) {
        Despesa despesa = despesaRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Despesa não encontrada com o id: " + id));
        despesaRepository.delete(despesa);
    }
	
	public Double calcularValorTotalDespesas() {
	    List<Despesa> despesas = despesaRepository.findAll();
	    Double valorTotal = 0.0;
	    for (Despesa despesa : despesas) {
	        valorTotal += despesa.getValor();
	    }
	    return valorTotal;
	}
	
	@Transactional
	public List<Despesa> listarDespesas(Long idUsuario) {
        Usuario usuario = usuarioRepository.findById(idUsuario).orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado com o id: " + idUsuario));
        return usuario.getDespesas();
    }
	
	@Transactional
	public List<Despesa> listarDespesaPeriodo(String dataInicio, String dataFim) {
		DateTimeFormatter formatter = DateTimeFormatter.ISO_INSTANT;
		Instant data1 = Instant.from(formatter.parse(dataInicio));
		Instant data2 = Instant.from(formatter.parse(dataFim));
	    return despesaRepository.findByDataDespesaBetween(data1, data2);
	}
	
	public List<Despesa> listarDespesasCategoria(Categoria categoria) {
	    return despesaRepository.findByCategoria(categoria.getCode());
	}

	public List<Despesa> buscarDespesasPorNome(String nome, Long id) {
	    Usuario obj = usuarioRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado com o id: " + id));
	    List<Despesa> prej = new ArrayList<>();
	    String normalizedNome = Normalizer.normalize(nome, Normalizer.Form.NFD);
	    String patternString = "(?i).*" + normalizedNome.replaceAll("\\p{M}", "") + ".*";
	    Pattern pattern = Pattern.compile(patternString);
	    for (Despesa despesa : obj.getDespesas()) {
	        String normalizedDespesaNome = Normalizer.normalize(despesa.getNome(), Normalizer.Form.NFD);
	        Matcher matcher = pattern.matcher(normalizedDespesaNome.replaceAll("\\p{M}", ""));
	        if (matcher.matches()) {
	            prej.add(despesa);
	        }
	    }
	    return prej;

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
	
	@Transactional
	public Double totalDespesasPeriodo(Long usuarioId, String dataInicio, String dataFim) {
		Usuario user = usuarioRepository.findById(usuarioId).orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado com o id: " + usuarioId));
		DateTimeFormatter formatter = DateTimeFormatter.ISO_INSTANT;
		Instant data1 = Instant.from(formatter.parse(dataInicio));
		Instant data2 = Instant.from(formatter.parse(dataFim));
		List<Despesa> totalpd = despesaRepository.findByDataDespesaBetween(data1, data2);
		Double total = 0.0;
		for (Despesa i : totalpd) {
			if (i.getUsuario().equals(user)) {
				total += i.getValor();
			}
		}
	    return total;
	}
	}


