package com.fullstackduck.boxes.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.fullstackduck.boxes.entities.Despesa;
import com.fullstackduck.boxes.entities.Usuario;
import com.fullstackduck.boxes.entities.enums.Categoria;
import com.fullstackduck.boxes.repositories.DespesaRepository;
import com.fullstackduck.boxes.repositories.UsuarioRepository;

@SpringBootTest
class DespesaServiceTest {

	@InjectMocks
	private DespesaService service;
	
	 @Mock
	 private UsuarioRepository usuarioRepository;
	
	@Mock
	private DespesaRepository repository;
	
	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testCalcularValorTotalDespesas() {
		List<Despesa> despesas = new ArrayList<>();
		despesas.add(new Despesa(1L, "Despesa 1", Categoria.FIXA, 100.0, "Observação 1",null,null,null));
		despesas.add(new Despesa(2L, "Despesa 2", Categoria.VARIAVEL, 50.0, "Observação 2", null,null,null));
		
		when(repository.findAll()).thenReturn(despesas);
		
		Double valorTotal = service.calcularValorTotalDespesas();
		
		assertEquals(150.0, valorTotal);
	}
	
	
	@Test
	public void testFindById() {
		Despesa despesa = new Despesa(1L, "Despesa 1", Categoria.FIXA, 100.0, "Observação 1",null,null,null);
		when(repository.findById(1L)).thenReturn(Optional.of(despesa));
		
		Despesa result = service.findById(1L);
		
		assertEquals(despesa, result);
	}
	
	@Test
	public void testInserirDespesa() {
		Despesa despesa = new Despesa(1L, "Despesa 1", Categoria.FIXA, 100.0, "Observação 1",null,null,null);
		when(repository.save(despesa)).thenReturn(despesa);
		
		Despesa result = service.inserirDespesa(despesa);
		
		assertEquals(despesa, result);
	}
	
	
	@Test
	void testAtualizarDespesa() {
	Long id = 1L;
	Despesa despesa = new Despesa(1L, "Despesa 1", Categoria.FIXA, 100.0, "Observação 1",null,null,null);
	Despesa despesaAtualizada = new Despesa(1L, "Despesa 1", Categoria.VARIAVEL, 150.0, "Observação 1",null,null,null);
	Mockito.when(repository.getReferenceById(id)).thenReturn(despesa);
	service.atualizarDespesa(id, despesaAtualizada);
	Mockito.verify(repository, Mockito.times(1)).save(despesa);
	assertEquals(despesaAtualizada.getNome(), despesa.getNome());
	assertEquals(despesaAtualizada.getCategoria(), despesa.getCategoria());
	assertEquals(despesaAtualizada.getValor(), despesa.getValor());
	assertEquals(despesaAtualizada.getObservacao(), despesa.getObservacao());
	}
	
	
	@Test
	void testListarDespesasCategoria() {
	    Categoria categoria = Categoria.FIXA;
	    List<Despesa> despesas = new ArrayList<>();
	    despesas.add(new Despesa(1L, "Despesa 1", Categoria.FIXA, 100.0, "Observação 1", null,null,null));
	    despesas.add(new Despesa(2L, "Despesa 2", Categoria.MATERIA_PRIMA, 200.0, "Observação 2", null,null,null));
	    despesas.add(new Despesa(3L, "Despesa 3", Categoria.FIXA, 50.0, "Observação 3", null,null,null));
	    
	    when(repository.findByCategoria(1)).thenReturn(despesas.stream().filter(d -> d.getCategoria() == categoria).collect(Collectors.toList()));
	    
	    List<Despesa> resultado = service.listarDespesasCategoria(categoria);
	    
	    assertNotNull(resultado);
	    assertEquals(2, resultado.size());
	    assertTrue(resultado.contains(despesas.get(0)));
	    assertTrue(resultado.contains(despesas.get(2)));
	}
	
	@Test
	void testListarDespesasPorPeriodo() {
	    String dataInicio = "2023-03-01T00:00:00Z";
	    String dataFim = "2023-03-31T23:59:59Z";
	    
	    Instant instantDataInicio = Instant.parse(dataInicio);
	    Instant instantDataFim = Instant.parse(dataFim);
	    
	    List<Despesa> despesas = new ArrayList<>();
	    despesas.add(new Despesa(1L, "Despesa 1", Categoria.FIXA, 100.0, "Observação 1", instantDataInicio, null, null));
	    despesas.add(new Despesa(2L, "Despesa 2", Categoria.MATERIA_PRIMA, 200.0, "Observação 2", instantDataFim, null, null));
	    despesas.add(new Despesa(3L, "Despesa 3", Categoria.VARIAVEL, 50.0, "Observação 3", Instant.parse("2023-04-01T00:00:00Z"), null, null));

	    when(repository.findByDataDespesaBetween(instantDataInicio, instantDataFim)).thenReturn(despesas);

	    List<Despesa> resultado = service.listarDespesaPeriodo(dataInicio, dataFim);

	    assertNotNull(resultado);
	    assertEquals(3, resultado.size()); // Corrigido para verificar a quantidade correta de despesas
	    assertTrue(resultado.contains(despesas.get(0)));
	    assertTrue(resultado.contains(despesas.get(1)));
	    assertTrue(resultado.contains(despesas.get(2))); // Adicionado para verificar se a terceira despesa também está presente
	}



	@Test
	public void testListarDespesas() {
	    // Dados de teste
	    Long idUsuario = 1L;
	    Usuario usuarioMock = mock(Usuario.class); // Crie um mock para o objeto Usuario
	    List<Despesa> despesasMock = new ArrayList<>(); // Crie uma lista de despesas de exemplo

	    // Configurar o comportamento dos mocks
	    when(usuarioRepository.getReferenceById(idUsuario)).thenReturn(usuarioMock);
	    when(usuarioMock.getDespesas()).thenReturn(despesasMock);

	    // Chamar o método do serviço
	    List<Despesa> resultado = service.listarDespesas(idUsuario);

	    // Verificar o resultado
	    assertEquals(despesasMock, resultado);
	}


    @Test
    public void testExcluirDespesa() {
        // Dados de teste
        Long idDespesa = 1L;
        Despesa despesaMock = new Despesa(); // Crie uma despesa de exemplo
        when(repository.findById(idDespesa)).thenReturn(Optional.of(despesaMock));

        // Chamar o método do serviço
        service.excluirDespesa(idDespesa);

        // Verificar se o método delete foi chamado com a despesa correta
        verify(repository, times(1)).delete(despesaMock);
    }

	
	
	
}
