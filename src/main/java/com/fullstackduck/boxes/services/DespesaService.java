package com.fullstackduck.boxes.services;

import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fullstackduck.boxes.entities.Despesa;
import com.fullstackduck.boxes.entities.Orcamento;
import com.fullstackduck.boxes.entities.Usuario;
import com.fullstackduck.boxes.entities.enums.Categoria;
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
		return despesaRepository.save(obj);
	}
	
	//atualiza dados da despesa no banco de dados
	
	public Despesa atualizarDespesa(Long id, Despesa obj) {
		try {
			Despesa entity = despesaRepository.getReferenceById(id);
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
        Usuario usuario = usuarioRepository.getReferenceById(idUsuario);
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
	
	}
