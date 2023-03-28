package com.fullstackduck.boxes.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.Instant;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.fullstackduck.boxes.entities.Despesa;
import com.fullstackduck.boxes.entities.Usuario;
import com.fullstackduck.boxes.entities.enums.Categoria;
import com.fullstackduck.boxes.entities.enums.Status;

@ExtendWith(SpringExtension.class)
public class DespesaTest {

	@Test
	public void criarDespesaComCategoria() {
		Usuario usuario = new Usuario(1L, "Usuário de Teste", "teste@teste.com", "123456", null, null, null, null, null);
		Despesa despesa = new Despesa(1L, "Despesa de Teste", Categoria.FIXA, 100.0, "Observação de Teste",
				Instant.now(), Status.ATIVO,  usuario);

		assertEquals("Despesa de Teste", despesa.getNome());
		assertEquals(Categoria.FIXA, despesa.getCategoria());
		assertEquals(100.0, despesa.getValor());
		assertEquals("Observação de Teste", despesa.getObservacao());
		assertNotNull(despesa.getDataDespesa());
		assertEquals(usuario, despesa.getUsuario());
	}

	@Test
	public void criarDespesaSemCategoria() {
		Usuario usuario = new Usuario(1L, "Usuário de Teste", "teste@teste.com", "123456", null, null, null, null, null);
		Despesa despesa = new Despesa(1L, "Despesa de Teste", null, 100.0, "Observação de Teste", Instant.now(), Status.ATIVO, 
				usuario);

		assertEquals("Despesa de Teste", despesa.getNome());
		assertEquals(null, despesa.getCategoria());
		assertEquals(100.0, despesa.getValor());
		assertEquals("Observação de Teste", despesa.getObservacao());
		assertNotNull(despesa.getDataDespesa());
		assertEquals(usuario, despesa.getUsuario());
	}

	@Test
	public void setarCategoria() {
		Despesa despesa = new Despesa();
		despesa.setCategoria(Categoria.FIXA);

		assertEquals(Categoria.FIXA, despesa.getCategoria());
	}

	@Test
	public void setarCategoriaNull() {
		Despesa despesa = new Despesa();
		despesa.setCategoria(null);

		assertEquals(null, despesa.getCategoria());
	}

	@Test
	public void setarCategoriaComCodigoNull() {
	    Despesa despesa = new Despesa();
	    try {
	        despesa.setCategoria(Categoria.valueOf(0));
	        fail("Deveria ter lançado uma exceção");
	    } catch (IllegalArgumentException e) {
	        assertNull(despesa.getCategoria());
	    }
	}

	@Test
	public void setarCategoriaComCodigoValido() {
		Despesa despesa = new Despesa();
		despesa.setCategoria(Categoria.valueOf(1));

		assertEquals(Categoria.FIXA, despesa.getCategoria());
	}

	@Test
	public void setarCategoriaComCodigoInvalido() {
	    Despesa despesa = new Despesa();
	    try {
	        despesa.setCategoria(Categoria.valueOf(10));
	        fail("Deveria ter lançado uma exceção");
	    } catch (IllegalArgumentException e) {
	        assertEquals(null, despesa.getCategoria());
	    }
	}
}
