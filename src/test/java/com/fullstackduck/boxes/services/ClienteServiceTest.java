package com.fullstackduck.boxes.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import com.fullstackduck.boxes.entities.Cliente;
import com.fullstackduck.boxes.entities.enums.StatusCliente;
import com.fullstackduck.boxes.repositories.ClienteRepository;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class ClienteServiceTest {

	@InjectMocks
	private ClienteService service;
	
	@Mock
	private ClienteRepository repository;
	
	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.openMocks(this);
	}

	
	@Test
	void testFindAll() {
	    List<Cliente> clientes = new ArrayList<>();
	    clientes.add(new Cliente(1L, "Cliente 1", "email1", "1111111111", Instant.now(), "123456789", Instant.now(), StatusCliente.ATIVA, null));
	    clientes.add(new Cliente(2L, "Cliente 2", "email2", "2222222222", Instant.now(), "987654321", Instant.now(), StatusCliente.INATIVO, null));
	    clientes.add(new Cliente(3L, "Cliente 3", "email3", "3333333333", Instant.now(), "987654321", Instant.now(), StatusCliente.ATIVA, null));
	    when(repository.findAll()).thenReturn(clientes);
	    
	    List<Cliente> result = service.findAll();
	    
	    assertEquals(clientes, result);
	}

	
	@Test
	void testFindById() {
		Cliente cliente = new Cliente(1L, "Cliente 1", "email", "1111113354", Instant.now(), "123456789", Instant.now(), StatusCliente.ATIVA, null);
		when(repository.findById(1L)).thenReturn(Optional.of(cliente));
		
		Cliente result = service.findById(1L);
		
		assertEquals(cliente, result);
	}
	
	@Test
	void testInserirCliente() {
		Cliente cliente = new Cliente(1L, "Cliente 1", "email", "1111113354", Instant.now(), "123456789", Instant.now(), StatusCliente.ATIVA, null);
		when(repository.save(cliente)).thenReturn(cliente);
		
		Cliente result = service.inserirCliente(cliente);
		
		assertEquals(cliente, result);
	}
	
	@Test
	void testAtualizarStatusCliente() {
		Long id = 1L;
		Cliente cliente = new Cliente(1L, "Cliente 1", "email", "1111113354", Instant.now(), "123456789", Instant.now(), StatusCliente.ATIVA, null);
		Cliente clienteAtualizada = new Cliente(1L, "Cliente 1", "email", "1111113354", Instant.now(), "123456789", Instant.now(), StatusCliente.INATIVO, null);
		when(repository.getReferenceById(id)).thenReturn(cliente);
		when(repository.save(cliente)).thenReturn(clienteAtualizada);
		
		Cliente result = service.atualizarStatusCliente(id, clienteAtualizada);
		
		assertEquals(clienteAtualizada, result);
	}
	
	@Test
	void testAtualizarCliente() {
	Long id = 1L;
	Cliente cliente = new Cliente(1L, "Cliente 1", "email", "1111113354", Instant.now(), "123456789", Instant.now(), StatusCliente.ATIVA, null);
	Cliente clienteAtualizada = new Cliente(1L, "Cliente 1", "emailFINAL", "1111113354", Instant.now(), "123456789", Instant.now(), StatusCliente.ATIVA, null);
	Mockito.when(repository.getReferenceById(id)).thenReturn(cliente);
	service.atualizarCliente(id, clienteAtualizada);
	Mockito.verify(repository, Mockito.times(1)).save(cliente);
	assertEquals(clienteAtualizada.getNome(), cliente.getNome());
	assertEquals(clienteAtualizada.getEmail(), cliente.getEmail());
	assertEquals(clienteAtualizada.getTelefone(), cliente.getTelefone());
	assertEquals(clienteAtualizada.getDataNascimento(), cliente.getDataNascimento());
	assertEquals(clienteAtualizada.getDocumento(), cliente.getDocumento());
	assertEquals(clienteAtualizada.getDataCliente(), cliente.getDataCliente());
	assertEquals(clienteAtualizada.getStatusCliente(), cliente.getStatusCliente());
	

	}
	
	@Test
    public void testListarClientePeriodo() {
        // Dados de teste
        String dataInicio = "2023-05-01T00:00:00Z";
        String dataFim = "2023-05-31T23:59:59Z";

        // Mock do repositório
        DateTimeFormatter formatter = DateTimeFormatter.ISO_INSTANT;
        Instant data1 = Instant.from(formatter.parse(dataInicio));
        Instant data2 = Instant.from(formatter.parse(dataFim));
        List<Cliente> clientesMock = new ArrayList<>(); // Crie uma lista de clientes de exemplo
        when(repository.findByDataClienteBetween(data1, data2)).thenReturn(clientesMock);

        // Chamar o método do serviço
        List<Cliente> resultado = service.listarClientePeriodo(dataInicio, dataFim);

        // Verificar o resultado
        assertEquals(clientesMock, resultado);
    }


	
	
	
}
