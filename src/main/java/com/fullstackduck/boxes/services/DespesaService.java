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

import com.fullstackduck.boxes.entities.Despesa;
import com.fullstackduck.boxes.entities.Usuario;
import com.fullstackduck.boxes.entities.enums.Categoria;
import com.fullstackduck.boxes.repositories.DespesaRepository;
import com.fullstackduck.boxes.repositories.UsuarioRepository;
import com.fullstackduck.boxes.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class DespesaService {

	@Autowired
	private DespesaRepository despesaRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Async
	public CompletableFuture<List<Despesa>> findAll(){
		List<Despesa> despesas = despesaRepository.findAll();
		return CompletableFuture.completedFuture(despesas);
	}
	
	@Async
	public CompletableFuture<Despesa> findById(Long id) {
		Optional<Despesa> obj = despesaRepository.findById(id);
		return CompletableFuture.completedFuture(obj.get());
	}

	@Async
	public CompletableFuture<Despesa> inserirDespesa(Despesa obj) {
		return CompletableFuture.completedFuture(despesaRepository.save(obj));
	}
	
	@Async
	public CompletableFuture<Despesa> atualizarDespesa(Long id, Despesa obj) {
		try {
			Despesa entity = despesaRepository.getReferenceById(id);
			atualizarDadosDespesa(entity, obj);
			return CompletableFuture.completedFuture(despesaRepository.save(entity));
		}catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}
	
	@Async
	private void atualizarDadosDespesa(Despesa entity, Despesa obj) {
		entity.setNome(obj.getNome());
		entity.setCategoria(obj.getCategoria());
		entity.setValor(obj.getValor());
		entity.setObservacao(obj.getObservacao());
		entity.setDataDespesa(obj.getDataDespesa());
	}
	
	@Async
	public CompletableFuture<Void> excluirDespesa(Long id) {
		Despesa despesa = despesaRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Despesa não encontrada com o id: " + id));
        despesaRepository.delete(despesa);
        return CompletableFuture.completedFuture(null);
    }
	
	@Async
	public CompletableFuture<Double> calcularValorTotalDespesas() {
		List<Despesa> despesas = despesaRepository.findAll();
	    Double valorTotal = 0.0;
	    for (Despesa despesa : despesas) {
	        valorTotal += despesa.getValor();
	    }
	    return CompletableFuture.completedFuture(valorTotal);
	}
	
	@Transactional
	@Async
	public CompletableFuture<List<Despesa>> listarDespesas(Long idUsuario) {
        Usuario usuario = usuarioRepository.getReferenceById(idUsuario);
        return CompletableFuture.completedFuture(usuario.getDespesas());
    }
	
	@Transactional
	@Async
	public CompletableFuture<List<Despesa>> listarDespesaPeriodo(String dataInicio, String dataFim) {
		DateTimeFormatter formatter = DateTimeFormatter.ISO_INSTANT;
		Instant data1 = Instant.from(formatter.parse(dataInicio));
		Instant data2 = Instant.from(formatter.parse(dataFim));
	    return CompletableFuture.completedFuture(despesaRepository.findByDataDespesaBetween(data1, data2));
	}
	
	@Async
	public CompletableFuture<List<Despesa>> listarDespesasCategoria(Categoria categoria) {
	    return CompletableFuture.completedFuture(despesaRepository.findByCategoria(categoria.getCode()));
	}
}
