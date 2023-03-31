package com.fullstackduck.boxes.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.fullstackduck.boxes.entities.Despesa;
import com.fullstackduck.boxes.entities.enums.Categoria;
import com.fullstackduck.boxes.entities.enums.Status;
import com.fullstackduck.boxes.repositories.DespesaRepository;

class DespesaServiceTest {

	@InjectMocks
	private DespesaService service;
	
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
	void testFindById() {
		Despesa despesa = new Despesa(1L, "Despesa 1", Categoria.FIXA, 100.0, "Observação 1",null,null,null);
		when(repository.findById(1L)).thenReturn(Optional.of(despesa));
		
		Despesa result = service.findById(1L);
		
		assertEquals(despesa, result);
	}
	
	@Test
	void testInserirDespesa() {
		Despesa despesa = new Despesa(1L, "Despesa 1", Categoria.FIXA, 100.0, "Observação 1",null,null,null);
		when(repository.save(despesa)).thenReturn(despesa);
		
		Despesa result = service.inserirDespesa(despesa);
		
		assertEquals(despesa, result);
	}
	
	@Test
	void testAtualizarStatusDespesa() {
		Long id = 1L;
		Despesa despesa = new Despesa(1L, "Despesa 1", Categoria.FIXA, 100.0, "Observação 1",null,Status.ATIVO,null);
		Despesa despesaAtualizada = new Despesa(1L, "Despesa 1", Categoria.FIXA, 100.0, "Observação 1",null,Status.INATIVO,null);
		when(repository.getReferenceById(id)).thenReturn(despesa);
		when(repository.save(despesa)).thenReturn(despesaAtualizada);
		
		Despesa result = service.atualizarStatusDespesa(id, despesaAtualizada);
		
		assertEquals(despesaAtualizada, result);
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
	
}
