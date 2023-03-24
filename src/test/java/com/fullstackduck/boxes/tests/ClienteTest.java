package com.fullstackduck.boxes.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.fullstackduck.boxes.entities.Cliente;
import com.fullstackduck.boxes.entities.Orcamento;
import com.fullstackduck.boxes.entities.Usuario;
import com.fullstackduck.boxes.entities.enums.StatusCliente;

public class ClienteTest {

	@Test
	public void criarClienteComTodosOsDados() {
		Usuario usuario = new Usuario(1L, "fulano", "senha123", null, null, null, null, null, null);
		Instant dataNascimento = Instant.now();
		Instant dataCadastro = Instant.now();
		Cliente cliente = new Cliente(1L, "João da Silva", "joao@gmail.com", "(11) 99999-9999", dataNascimento,
				"12345678901", dataCadastro, StatusCliente.ATIVA, usuario);
		assertNotNull(cliente.getId());
		assertEquals("João da Silva", cliente.getNome());
		assertEquals("joao@gmail.com", cliente.getEmail());
		assertEquals("(11) 99999-9999", cliente.getTelefone());
		assertEquals(dataNascimento, cliente.getDataNascimento());
		assertEquals("12345678901", cliente.getDocumento());
		assertEquals(dataCadastro, cliente.getDataCadastro());
		assertEquals(StatusCliente.ATIVA, cliente.getStatusCliente());
		assertEquals(usuario, cliente.getUsuario());
	}

	

	@Test
	public void adicionarOrcamentoAoCliente() {
		Usuario usuario = new Usuario(1L, "fulano", "senha123", null, null, null, null, null, null);
		Cliente cliente = new Cliente(1L, "João da Silva", "joao@gmail.com", "(11) 99999-9999", Instant.now(),
				"12345678901", Instant.now(), StatusCliente.ATIVA, usuario);
		Orcamento orcamento = new Orcamento();
		List<Orcamento> orcamentos = new ArrayList<>();
		orcamentos.add(orcamento);
		cliente.setOrcamentos(orcamentos);
		assertEquals(1, cliente.getOrcamentos().size());
		assertEquals(orcamento, cliente.getOrcamentos().get(0));
	}
	
	
}
