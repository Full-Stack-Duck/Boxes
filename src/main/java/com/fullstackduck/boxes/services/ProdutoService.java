package com.fullstackduck.boxes.services;


import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.fullstackduck.boxes.entities.Produto;
import com.fullstackduck.boxes.entities.Usuario;
import com.fullstackduck.boxes.entities.enums.Status;
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
	
	public ProdutoService(ProdutoRepository repository) {
	    this.produtoRepository = repository;
	  }
	
	
	public List<Produto> findAll(){
		return produtoRepository.findAll();
	}
	
	public Produto findById(Long id) {
		Optional<Produto> obj = produtoRepository.findById(id);
		return obj.get();
	}

	//insere usuario no banco de dados
	
	public Produto inserirProduto(Produto obj) {
		obj.setStatus(Status.ATIVO);
		obj.setQuantidade(0);
		// Extrai o ID do usuário do token JWT
	    Long userId = getUserIdFromToken();
	    
	    // Busca o usuário pelo ID
	    Usuario user = usuarioRepository.findById(userId).orElse(null);

        if (user != null) {
            // Associa o produto ao usuário
            obj.setUsuario(user);
            // Salva o produto no banco de dados
        }
		return produtoRepository.save(obj);
	}
	
	//atualiza status do usuario no banco de dados
	
	public Produto atualizarStatusProduto(Long id, Produto obj) {
		try {
			Produto entity = produtoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Produto não encontrado com o id: " + id));
			atualizarStatus(entity, obj);
			return produtoRepository.save(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}
	
	//atualiza dados do usuario no banco de dados
	
	public Produto atualizarProduto(Long id, Produto obj) {
		try {
			Produto entity = produtoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Produto não encontrado com o id: " + id));
			atualizarDados(entity, obj);
			return produtoRepository.save(entity);
		}catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}
	
	private void atualizarDados(Produto entity, Produto obj) {
		entity.setNome(obj.getNome());
		entity.setValor(obj.getValor());
		entity.setCategoria(obj.getCategoria());
		entity.setQuantidade(obj.getQuantidade());
		entity.setTipo(obj.getTipo());
		entity.setObservacao(obj.getObservacao());
	}
	
	public List<Produto> listarProdutos(Long idUsuario) {
        Usuario usuario = usuarioRepository.findById(idUsuario).orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado com o id: " + idUsuario));
        return usuario.getProdutos();
    }
	
	private void atualizarStatus(Produto entity, Produto obj) {
		entity.setStatus(Status.INATIVO);
	}
	
	public List<Produto> listarProdutosCategoria(TipoArmazenamento categoria, Long id) {
		Usuario obj = usuarioRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado com o id: " + id));
		List<Produto> prod = new ArrayList<>();
		for (Produto i : obj.getProdutos()) {
			if(i.getCategoria() == categoria && i.getStatus().equals(Status.ATIVO)) {
				prod.add(i);
			}
		}
		//List<Produto> prod = listarProdutos(id);
	    return prod;
	}

	public List<Produto> listarProdutosTipo(TipoProduto tipo) {
	    return produtoRepository.findByTipo(tipo);
	}
	
	public List<Produto> buscarProdutosPorNome(String nome, Long id) {
	    Usuario obj = usuarioRepository.getReferenceById(id);
	    List<Produto> prod = new ArrayList<>();
	    String normalizedNome = Normalizer.normalize(nome, Normalizer.Form.NFD);
	    String patternString = "(?i).*" + normalizedNome.replaceAll("\\p{M}", "") + ".*";
	    Pattern pattern = Pattern.compile(patternString);
	    for (Produto produto : obj.getProdutos()) {
	        String normalizedProdutoNome = Normalizer.normalize(produto.getNome(), Normalizer.Form.NFD);
	        Matcher matcher = pattern.matcher(normalizedProdutoNome.replaceAll("\\p{M}", ""));
	        if (matcher.matches()) {
	            prod.add(produto);
	        }
	    }
	    return prod;
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
	
	public Integer totalDeProdutos(Long usuarioId) {
		Usuario user = usuarioRepository.findById(usuarioId).orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado com o id: " + usuarioId));
		List<Produto> prod = user.getProdutos();
		Integer total = 0;
		for (Produto i : prod) {
			if (i.getQuantidade() != null) {
				total += i.getQuantidade();
			}
		}
		return total;
	}
}
