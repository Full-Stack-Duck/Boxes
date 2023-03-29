package com.fullstackduck.boxes.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fullstackduck.boxes.entities.Produto;
import com.fullstackduck.boxes.repositories.ProdutoRepository;
import com.fullstackduck.boxes.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service //Registro de componente
public class ProdutoService {

	@Autowired
	private ProdutoRepository repository;
	
	@Transactional
	public List<Produto> findAll(){
		return repository.findAll();
	}
	@Transactional
	public Produto findById(Long id) {
		Optional<Produto> obj = repository.findById(id);
		return obj.get();
	}

	//insere usuario no banco de dados
	@Transactional
	public Produto inserirProduto(Produto obj) {
		return repository.save(obj);
	}
	
	//atualiza status do usuario no banco de dados
	@Transactional
	public Produto atualizarStatusProduto(Long id, Produto obj) {
		try {
			Produto entity = repository.getReferenceById(id);
			atualizarStatus(entity, obj);
			return repository.save(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}
	
	//atualiza dados do usuario no banco de dados
	@Transactional
	public Produto atualizarProduto(Long id, Produto obj) {
		try {
			Produto entity = repository.getReferenceById(id);
			atualizarDados(entity, obj);
			return repository.save(entity);
		}catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}
	@Transactional
	private void atualizarDados(Produto entity, Produto obj) {
		entity.setNome(obj.getNome());
		entity.setValor(obj.getValor());
		entity.setCategoria(obj.getCategoria());
		entity.setQuantidade(obj.getQuantidade());
		entity.setTipo(obj.getTipo());
		entity.setObservacao(obj.getObservacao());
	}
	@Transactional
	private void atualizarStatus(Produto entity, Produto obj) {
		entity.setStatus(obj.getStatus());
	}
}
