/*
package com.fullstackduck.boxes.resources;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.awt.PageAttributes.MediaType;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fullstackduck.boxes.entities.Produto;
import com.fullstackduck.boxes.entities.enums.Status;
import com.fullstackduck.boxes.entities.enums.TipoArmazenamento;
import com.fullstackduck.boxes.entities.enums.TipoProduto;
import com.fullstackduck.boxes.repositories.ProdutoRepository;
import com.fullstackduck.boxes.services.ProdutoService;


@DisplayName("Produto Resource Test")
class ProdutoResourceTest {

  @Mock
  private ProdutoService produtoService;

  @InjectMocks
  private ProdutoResource produtoResource;

  private MockMvc mockMvc;

  @BeforeEach
  void setup() {
    MockitoAnnotations.openMocks(this);
    mockMvc = MockMvcBuilders.standaloneSetup(produtoResource).build();
  }

  @Test
  @DisplayName("Get All Produtos")
  void testGetAllProdutos() throws Exception {
    Produto produto1 = new Produto(1L,"Produto 1", 50.0, TipoArmazenamento.ESTOCAVEL,10, TipoProduto.BEBIDA, Status.ATIVO, "Sem observações",null, null);
    Produto produto2 = new Produto(2L,"Produto 2", 100.0, TipoArmazenamento.ESTOCAVEL,5, TipoProduto.DOCE,Status.INATIVO, "Com observações",null, null);

    List<Produto> produtos = Arrays.asList(produto1, produto2);

    when(produtoService.findAll()).thenReturn(produtos);

    mockMvc.perform(get("/produtos"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.size()", is(produtos.size())))
            .andExpect(jsonPath("$[0].id", is(produto1.getId().intValue())))
            .andExpect(jsonPath("$[0].nome", is(produto1.getNome())))
            .andExpect(jsonPath("$[1].id", is(produto2.getId().intValue())))
            .andExpect(jsonPath("$[1].nome", is(produto2.getNome())));

    verify(produtoService, times(1)).findAll();
  }

  @Test
  @DisplayName("Get Produto by Id")
  void testGetProdutoById() throws Exception {
    Long produtoId = 1L;
    Produto produto = new Produto(produtoId, "Produto 1", 50.0, TipoArmazenamento.ESTOCAVEL, 10, TipoProduto.BEBIDA, Status.ATIVO, "Sem observações", null, null);

    when(produtoService.findById(produtoId)).thenReturn(produto);

    mockMvc.perform(get("/produtos/{id}", produtoId))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id", is(produto.getId().intValue())))
            .andExpect(jsonPath("$.nome", is(produto.getNome())));

    verify(produtoService, times(1)).findById(produtoId);
  }

  @Test
  @DisplayName("Create Produto")
  void testCreateProduto() throws Exception {
    Long produtoId = 1L;
    Produto produto = new Produto(produtoId,"Produto 1", 50.0, TipoArmazenamento.ESTOCAVEL,10, TipoProduto.BEBIDA, Status.ATIVO, "Sem observações",null, null);

    when(produtoService.inserirProduto(Mockito.any(Produto.class))).thenReturn(produto);

    mockMvc.perform(post("/produtos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(asJsonString(produto)))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.id", is(produtoId.intValue())))
            .andExpect(jsonPath("$.nome", is(produto.getNome())));

    verify(produtoService, times(1)).inserirProduto(Mockito.any(Produto.class));
  }

  @Test
  @DisplayName("Update Produto Status")
  void testAtualizarStatusProduto() {
      Long produtoId = 1L;
      Produto produto = new Produto(1L, "Produto 1", 50.0, TipoArmazenamento.ESTOCAVEL, 10, TipoProduto.BEBIDA, Status.ATIVO, "Sem observações", null, null);
      Produto produtoAtualizado = new Produto(1L, "Produto 1", 50.0, TipoArmazenamento.ESTOCAVEL, 10, TipoProduto.BEBIDA, Status.INATIVO, "Sem observações", null, null);

      when(repository.getReferenceById(produtoId)).thenReturn(produto);
      when(repository.save(produto)).thenReturn(produtoAtualizado);

      Produto produtoAtualizadoStatus = produtoService.atualizarStatusProduto(produtoId, produtoAtualizado);

      assertThat(produtoAtualizadoStatus.getStatus()).isEqualTo(Status.INATIVO);
  }

  @Test
  @DisplayName("Update Produto")
  void testAtualizarProduto() {
      Long produtoId = 1L;
      Produto produto = new Produto(1L, "Produto 1", 50.0, TipoArmazenamento.ESTOCAVEL, 10, TipoProduto.BEBIDA, Status.ATIVO, "Sem observações", null, null);
      Produto produtoAtualizado = new Produto(1L, "Produto 1 atualizado", 60.0, TipoArmazenamento.ESTOCAVEL, 20, TipoProduto.BEBIDA, Status.ATIVO, "Observação atualizada", null, null);

      when(ProdutoRepository.getReferenceById(produtoId)).thenReturn(produto);
      when(ProdutoRepository.save(produto)).thenReturn(produtoAtualizado);

      Produto produtoAtualizadoInfo = produtoService.atualizarProduto(produtoId, produtoAtualizado);

      assertThat(produtoAtualizadoInfo.getNome()).isEqualTo("Produto 1 atualizado"));
      assertThat(produtoAtualizadoInfo.getValor()).isEqualTo(60.0);
      assertThat(produtoAtualizadoInfo.getCategoria()).isEqualTo(TipoArmazenamento.ESTOCAVEL);
      assertThat(produtoAtualizadoInfo.getQuantidade()).isEqualTo(20);
      assertThat(produtoAtualizadoInfo.getTipo()).isEqualTo(TipoProduto.BEBIDA);
      assertThat(produtoAtualizadoInfo.getObservacao()).isEqualTo("Observação atualizada");
  }

}

*/
