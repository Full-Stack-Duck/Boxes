package com.fullstackduck.boxes.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.fullstackduck.boxes.entities.Produto;
import com.fullstackduck.boxes.entities.enums.Status;
import com.fullstackduck.boxes.entities.enums.TipoArmazenamento;
import com.fullstackduck.boxes.entities.enums.TipoProduto;
import com.fullstackduck.boxes.repositories.ProdutoRepository;

@DisplayName("Produto Service Test")
class ProdutoServiceTest {

  @Mock private ProdutoRepository repository;

  private ProdutoService produtoService;

  @BeforeEach
  void setup() {
    MockitoAnnotations.openMocks(this);
    produtoService = new ProdutoService(repository);
  }

  @Test
  @DisplayName("Find All Produtos")
  void testFindAll() {
    Produto produto1 = new Produto(1L,"Produto 1", 50.0, TipoArmazenamento.ESTOCAVEL,10, TipoProduto.BEBIDA, Status.ATIVO, "Sem observações",null);
    Produto produto2 = new Produto(2L,"Produto 2", 100.0, TipoArmazenamento.ESTOCAVEL,5, TipoProduto.DOCE,Status.INATIVO, "Com observações",null);

    when(repository.findAll()).thenReturn(Arrays.asList(produto1, produto2));

    List<Produto> produtos = produtoService.findAll();

    assertThat(produtos).isNotNull();
    assertThat(produtos.size()).isEqualTo(2);
    assertThat(produtos.get(0).getNome()).isEqualTo("Produto 1");
    assertThat(produtos.get(1).getNome()).isEqualTo("Produto 2");

    verify(repository, times(1)).findAll();
  }

  @Test
  @DisplayName("Find Produto by Id")
  void testFindById() {
    Produto produto = new Produto(1L,"Produto 1", 50.0, TipoArmazenamento.ESTOCAVEL,10, TipoProduto.BEBIDA, Status.ATIVO, "Sem observações",null);

    when(repository.findById(1L)).thenReturn(Optional.of(produto));

    Produto result = produtoService.findById(1L);

    assertThat(result).isNotNull();
    assertThat(result.getId()).isEqualTo(1L);
    assertThat(result.getNome()).isEqualTo("Produto 1");

    verify(repository, times(1)).findById(1L);
  }

  @Test
  @DisplayName("Inserir Produto")
  void testInserirProduto() {
    Produto produto = new Produto(1L,"Produto 1", 50.0, TipoArmazenamento.ESTOCAVEL,10, TipoProduto.BEBIDA, Status.ATIVO, "Sem observações",null);

    when(repository.save(produto)).thenReturn(produto);

    Produto result = produtoService.inserirProduto(produto);

    assertThat(result).isNotNull();
    assertThat(result.getId()).isEqualTo(1L);
    assertThat(result.getNome()).isEqualTo("Produto 1");

    verify(repository, times(1)).save(produto);
  }
  
  

  @Test
  @DisplayName("Update Produto Status")
  void testAtualizarStatusProduto() {
      Long produtoId = 1L;
      Produto produto = new Produto(1L, "Produto 1", 50.0, TipoArmazenamento.ESTOCAVEL, 10, TipoProduto.BEBIDA, Status.ATIVO, "Sem observações", null);
      Produto produtoAtualizado = new Produto(1L, "Produto 1", 50.0, TipoArmazenamento.ESTOCAVEL, 10, TipoProduto.BEBIDA, Status.INATIVO, "Sem observações", null);

      when(repository.getReferenceById(produtoId)).thenReturn(produto);
      when(repository.save(produto)).thenReturn(produtoAtualizado);

      Produto produtoAtualizadoStatus = produtoService.atualizarStatusProduto(produtoId, produtoAtualizado);

      assertThat(produtoAtualizadoStatus.getStatus()).isEqualTo(Status.INATIVO);
  }


  
  @Test
  @DisplayName("Update Produto")
  void testAtualizarProduto() {
  Long produtoId = 1L;
  Produto produto = new Produto(1L,"Produto 1", 50.0, TipoArmazenamento.ESTOCAVEL,10, TipoProduto.BEBIDA, Status.ATIVO, "Sem observações",null);
  Produto produtoAtualizado = new Produto(1L,"Produto 1 atualizado", 60.0, TipoArmazenamento.ESTOCAVEL,20, TipoProduto.BEBIDA, Status.ATIVO, "Observação atualizada",null); // Corrigido, estava criando um objeto Produto com um nome diferente do que foi atualizado

  when(repository.getReferenceById(produtoId)).thenReturn(produto);
  when(repository.save(produto)).thenReturn(produtoAtualizado);

  Produto produtoAtualizadoInfo = produtoService.atualizarProduto(produtoId, produtoAtualizado);

  assertThat(produtoAtualizadoInfo.getNome()).isEqualTo("Produto 1 atualizado");
  assertThat(produtoAtualizadoInfo.getValor()).isEqualTo(60.0);
  assertThat(produtoAtualizadoInfo.getCategoria()).isEqualTo(TipoArmazenamento.ESTOCAVEL);
  assertThat(produtoAtualizadoInfo.getQuantidade()).isEqualTo(20);
  assertThat(produtoAtualizadoInfo.getTipo()).isEqualTo(TipoProduto.BEBIDA);
  assertThat(produtoAtualizadoInfo.getObservacao()).isEqualTo("Observação atualizada");

  }
  
  
  
}

