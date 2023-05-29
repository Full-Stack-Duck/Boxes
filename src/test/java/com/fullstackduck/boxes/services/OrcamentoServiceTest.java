package com.fullstackduck.boxes.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.fullstackduck.boxes.entities.ItensOrcamento;
import com.fullstackduck.boxes.entities.Orcamento;
import com.fullstackduck.boxes.entities.Produto;
import com.fullstackduck.boxes.entities.enums.Status;
import com.fullstackduck.boxes.entities.enums.TipoEntrega;
import com.fullstackduck.boxes.repositories.ItensOrcamentoRepository;
import com.fullstackduck.boxes.repositories.OrcamentoRepository;
import com.fullstackduck.boxes.repositories.ProdutoRepository;


	
public class OrcamentoServiceTest {

  @Mock
  private OrcamentoRepository orcamentoRepository;

  @Mock
  private ProdutoRepository produtoRepository;

  @Mock
  private ItensOrcamentoRepository itensRepository;

  @Mock
  private ItensOrcamentoService itensService;

  @InjectMocks
  private OrcamentoService orcamentoService;
 
  
  @BeforeEach
  public void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  public void testFindAll() {
    // given
    List<Orcamento> orcamentos = new ArrayList<>();
    orcamentos.add(new Orcamento());
    when(orcamentoRepository.findAll()).thenReturn(orcamentos);

    // when
    List<Orcamento> result = orcamentoService.findAll();

    // then
    assertEquals(orcamentos, result);
    verify(orcamentoRepository).findAll();
  }

  @Test
  public void testFindById() {
    // given
    Long id = 1L;
    Orcamento orcamento = new Orcamento();
    orcamento.setId(id);
    when(orcamentoRepository.findById(id)).thenReturn(Optional.of(orcamento));

    // when
    Orcamento result = orcamentoService.findById(id);

    // then
    assertEquals(orcamento, result);
    verify(orcamentoRepository).findById(id);
  }

  @Test
  public void testInserirOrcamento() {
    // given
    Orcamento orcamento = new Orcamento();
    when(orcamentoRepository.save(orcamento)).thenReturn(orcamento);

    // when
    Orcamento result = orcamentoService.inserirOrcamento(orcamento);

    // then
    assertEquals(orcamento, result);
    verify(orcamentoRepository).save(orcamento);
  }

  @Test
  public void testAtualizarStatusOrcamento() {
    // given
    Long id = 1L;
    Orcamento entity = new Orcamento();
    entity.setId(id);
    Orcamento obj = new Orcamento();
    obj.setStatus(Status.INATIVO);
    when(orcamentoRepository.getReferenceById(id)).thenReturn(entity);
    when(orcamentoRepository.save(entity)).thenReturn(entity);

    // when
    Orcamento result = orcamentoService.atualizarStatusOrcamento(id, obj);

    // then
    assertEquals(entity, result);
    assertEquals(obj.getStatus(), entity.getStatus());
    verify(orcamentoRepository).getReferenceById(id);
    verify(orcamentoRepository).save(entity);
  }

  @Test
  public void testAtualizarOrcamento() {
    // given
    Long id = 1L;
    Orcamento entity = new Orcamento();
    entity.setId(id);
    Orcamento obj = new Orcamento();
    obj.setTipoEntrega(TipoEntrega.RETIRADA);
    when(orcamentoRepository.getReferenceById(id)).thenReturn(entity);
    when(orcamentoRepository.save(entity)).thenReturn(entity);

    // when
    Orcamento result = orcamentoService.atualizarOrcamento(id, obj);

    // then
    assertEquals(entity, result);
    assertEquals(obj.getTipoEntrega(), entity.getTipoEntrega());
    verify(orcamentoRepository).getReferenceById(id);
    verify(orcamentoRepository).save(entity);
  }


  @Test
  public void testAdicionarItem() {
      // Criando um objeto Orcamento para ser usado no teste
      Orcamento orcamento = new Orcamento();
      orcamento.setId(1L);

      // Criando um objeto Produto para ser usado no teste
      Produto produto = new Produto();
      produto.setId((long) 1);
      produto.setValor(50.0);

      // Criando um objeto ItensOrcamento para ser usado no teste
      ItensOrcamento item = new ItensOrcamento();
      item.setProduto(produto);
      item.setPrecoUnit(produto.getValor());
      item.setQuantidade(2);
      item.setOrcamento(orcamento);
      item.setPrecoTotal(produto.getValor() * 2);

      // Mockando o comportamento dos repositórios e services
      when(orcamentoRepository.getReferenceById(1L)).thenReturn(orcamento);
      when(produtoRepository.getReferenceById(1)).thenReturn(produto);
      when(itensRepository.save(item)).thenReturn(item);
      when(orcamentoRepository.save(orcamento)).thenReturn(orcamento);

      // Chamando o método a ser testado
      Orcamento resultado = orcamentoService.adicionarItem(1L, 1, item);

      // Verificando se o método chamado foi o esperado e se o resultado está correto
      verify(orcamentoRepository).getReferenceById(1L);
      verify(produtoRepository).getReferenceById(1);
      verify(itensRepository).save(item);
      verify(orcamentoRepository).save(orcamento);
      assertEquals(1L, resultado.getId().longValue());
      assertEquals(1, resultado.getItens().size());
      assertEquals(item, resultado.getItens().iterator().next());
  }

}


        
        


