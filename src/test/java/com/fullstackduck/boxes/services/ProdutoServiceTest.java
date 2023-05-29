package com.fullstackduck.boxes.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

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
    produtoService = new ProdutoService();
  }

  @Test
  @DisplayName("Find All Produtos")
  void testFindAll() throws Exception {
      Produto produto1 = new Produto(1L,"Produto 1", 50.0, TipoArmazenamento.ESTOCAVEL,10, TipoProduto.BEBIDA, Status.ATIVO, "Sem observações",null);
      Produto produto2 = new Produto(2L,"Produto 2", 100.0, TipoArmazenamento.ESTOCAVEL,5, TipoProduto.DOCE,Status.INATIVO, "Com observações",null);

      when(repository.findAll()).thenReturn(Arrays.asList(produto1, produto2));

      CompletableFuture<List<Produto>> produtos = produtoService.findAll();

      assertThat(produtos).isNotNull();

      // Extrai a lista de produtos do CompletableFuture usando o método join()
      List<Produto> produtosList = produtos.join();

      assertThat(produtosList.size()).isEqualTo(2);
      assertThat(produtosList.get(0).getNome()).isEqualTo("Produto 1");
      assertThat(produtosList.get(1).getNome()).isEqualTo("Produto 2");

      verify(repository, times(1)).findAll();
  }


  @Test
  @DisplayName("Find Produto by Id")
  void testFindById() throws Exception {
      Produto produto = new Produto(1L,"Produto 1", 50.0, TipoArmazenamento.ESTOCAVEL,10, TipoProduto.BEBIDA, Status.ATIVO, "Sem observações",null);

      when(repository.findById(1L)).thenReturn(Optional.of(produto));

      CompletableFuture<Produto> result = produtoService.findById(1L);

      assertThat(result).isNotNull();

      // Extrai o produto do CompletableFuture usando o método join()
      Produto produtoResult = result.join();

      assertThat(produtoResult.getId()).isEqualTo(1L);
      assertThat(produtoResult.getNome()).isEqualTo("Produto 1");

      verify(repository, times(1)).findById(1L);
  }


  @Test
  @DisplayName("Inserir Produto")
  void testInserirProduto() {
      Produto produto = new Produto(1L, "Produto 1", 50.0, TipoArmazenamento.ESTOCAVEL, 10, TipoProduto.BEBIDA, Status.ATIVO, "Sem observações", null);

      when(repository.save(produto)).thenReturn(produto);

      CompletableFuture<Produto> result = produtoService.inserirProduto(produto);

      assertThat(result).isNotNull();

      // Extrai o produto do CompletableFuture usando o método join()
      Produto produtoResult = result.join();

      assertThat(produtoResult.getId()).isEqualTo(1L);
      assertThat(produtoResult.getNome()).isEqualTo("Produto 1");

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

      CompletableFuture<Produto> produtoAtualizadoStatus = produtoService.atualizarStatusProduto(produtoId, produtoAtualizado);

      // Extrai o produto atualizado do CompletableFuture usando o método join()
      Produto produtoAtualizadoResult = produtoAtualizadoStatus.join();

      assertThat(produtoAtualizadoResult.getStatus()).isEqualTo(Status.INATIVO);
  }



  
  @Test
  @DisplayName("Update Produto")
  void testAtualizarProduto() {
      Long produtoId = 1L;
      Produto produto = new Produto(1L, "Produto 1", 50.0, TipoArmazenamento.ESTOCAVEL, 10, TipoProduto.BEBIDA, Status.ATIVO, "Sem observações", null);
      Produto produtoAtualizado = new Produto(1L, "Produto 1 atualizado", 60.0, TipoArmazenamento.ESTOCAVEL, 20, TipoProduto.BEBIDA, Status.ATIVO, "Observação atualizada", null);

      when(repository.getReferenceById(produtoId)).thenReturn(produto);
      when(repository.save(produto)).thenReturn(produtoAtualizado);

      CompletableFuture<Produto> produtoAtualizadoInfo = produtoService.atualizarProduto(produtoId, produtoAtualizado);

      // Extrai as informações do produto atualizado do CompletableFuture usando o método join()
      Produto produtoAtualizadoResult = produtoAtualizadoInfo.join();

      assertThat(produtoAtualizadoResult.getNome()).isEqualTo("Produto 1 atualizado");
      assertThat(produtoAtualizadoResult.getValor()).isEqualTo(60.0);
      assertThat(produtoAtualizadoResult.getCategoria()).isEqualTo(TipoArmazenamento.ESTOCAVEL);
      assertThat(produtoAtualizadoResult.getQuantidade()).isEqualTo(20);
      assertThat(produtoAtualizadoResult.getTipo()).isEqualTo(TipoProduto.BEBIDA);
      assertThat(produtoAtualizadoResult.getObservacao()).isEqualTo("Observação atualizada");
  }

  
  
}

