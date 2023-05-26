package com.fullstackduck.boxes.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fullstackduck.boxes.entities.Produto;
import com.fullstackduck.boxes.entities.Usuario;
import com.fullstackduck.boxes.entities.enums.TipoArmazenamento;
import com.fullstackduck.boxes.entities.enums.TipoProduto;
import com.fullstackduck.boxes.repositories.ProdutoRepository;
import com.fullstackduck.boxes.repositories.UsuarioRepository;
import com.fullstackduck.boxes.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service //Registro de componente
public class ProdutoService {

	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Async
	public List<Produto> findAll(){
		return produtoRepository.findAll();
	}
	
	@Async
	public Produto findById(Long id) {
		Optional<Produto> obj = produtoRepository.findById(id);
		return obj.get();
	}

	//insere usuario no banco de dados
	@Async
	public Produto inserirProduto(Produto obj) {
		return produtoRepository.save(obj);
	}
	
	//atualiza status do usuario no banco de dados
	@Async
	public Produto atualizarStatusProduto(Long id, Produto obj) {
		try {
			Produto entity = produtoRepository.getReferenceById(id);
			atualizarStatus(entity, obj);
			return produtoRepository.save(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}
	
	//atualiza dados do usuario no banco de dados
	@Async
	public Produto atualizarProduto(Long id, Produto obj) {
		try {
			Produto entity = produtoRepository.getReferenceById(id);
			atualizarDados(entity, obj);
			return produtoRepository.save(entity);
		}catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}
	
	@Async
	private void atualizarDados(Produto entity, Produto obj) {
		entity.setNome(obj.getNome());
		entity.setValor(obj.getValor());
		entity.setCategoria(obj.getCategoria());
		entity.setQuantidade(obj.getQuantidade());
		entity.setTipo(obj.getTipo());
		entity.setObservacao(obj.getObservacao());
	}
	
	@Transactional
	@Async
	public List<Produto> listarProdutos(Long idUsuario) {
        Usuario usuario = usuarioRepository.getReferenceById(idUsuario);
        return usuario.getProdutos();
    }
	
	@Async
	private void atualizarStatus(Produto entity, Produto obj) {
		entity.setStatus(obj.getStatus());
	}
	
	@Async
	public List<Produto> listarProdutosCategoria(TipoArmazenamento categoria) {
	    return produtoRepository.findByCategoria(categoria);
	}

	@Async
	public List<Produto> listarProdutosTipo(TipoProduto tipo) {
	    return produtoRepository.findByTipo(tipo);
	}
}
