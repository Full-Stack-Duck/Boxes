package com.fullstackduck.boxes.services;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fullstackduck.boxes.entities.Despesa;
import com.fullstackduck.boxes.entities.enums.Categoria;
import com.fullstackduck.boxes.repositories.DespesaRepository;
import com.fullstackduck.boxes.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service //Registro de componente
public class DespesaService {

	@Autowired
	private DespesaRepository repository;
	
	
	public List<Despesa> findAll(){
		return repository.findAll();
	}
	
	
	public Despesa findById(Long id) {
		Optional<Despesa> obj = repository.findById(id);
		return obj.get();
	}

	//insere despesa no banco de dados
	
	public Despesa inserirDespesa(Despesa obj) {
		return repository.save(obj);
	}
	
	//atualiza status da despesa no banco de dados
	
	public Despesa atualizarStatusDespesa(Long id, Despesa obj) {
		try {
			Despesa entity = repository.getReferenceById(id);
			atualizarDadosDespesa(entity, obj);
			return repository.save(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}
	
	//atualiza dados da despesa no banco de dados
	
	public Despesa atualizarDespesa(Long id, Despesa obj) {
		try {
			Despesa entity = repository.getReferenceById(id);
			atualizarDadosDespesa(entity, obj);
			return repository.save(entity);
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
        Despesa despesa = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Despesa não encontrada com o id: " + id));
        repository.delete(despesa);
    }
	
	public Double calcularValorTotalDespesas() {
	    List<Despesa> despesas = repository.findAll();
	    Double valorTotal = 0.0;
	    for (Despesa despesa : despesas) {
	        valorTotal += despesa.getValor();
	    }
	    return valorTotal;
	}
	
	public List<Despesa> listarDespesasCategoria(Categoria categoria) {
	    return repository.findByCategoria(categoria.getCode());
	}
	
	
	public List<Despesa> listarDespesasPorPeriodo(Instant dataInicio, Instant dataFim) {
	    return repository.findByDataDespesaBetween(dataInicio, dataFim);
	}
	
	
	
	
	
	
	
	
	
}