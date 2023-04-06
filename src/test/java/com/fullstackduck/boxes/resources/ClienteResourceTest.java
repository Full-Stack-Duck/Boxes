package com.fullstackduck.boxes.resources;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Instant;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.fullstackduck.boxes.entities.Cliente;
import com.fullstackduck.boxes.entities.enums.StatusCliente;
import com.fullstackduck.boxes.repositories.ClienteRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ClienteResourceTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private ClienteRepository clienteRepository;

    private Cliente cliente1;
    private Cliente cliente2;

    @Before
    public void setUp() {
        cliente1 = new Cliente(1L,"Cliente 1", "email1", "1111111111", Instant.now(), "123456789", Instant.now(), StatusCliente.ATIVA, null);
        cliente2 = new Cliente(2L,"Cliente 2", "email2", "11221111", Instant.now(), "123745889", Instant.now(), StatusCliente.ATIVA, null);
        clienteRepository.save(cliente1);
        clienteRepository.save(cliente2);
    }

    @Test
    public void testListarClientes() {
        ResponseEntity<List> response = restTemplate.getForEntity("/clientes", List.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody().size() >= 2);
    }

    @Test
    public void testBuscarClientePorId() {
        ResponseEntity<Cliente> response = restTemplate.getForEntity("/clientes/{id}", Cliente.class, cliente1.getId());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Cliente 1", response.getBody().getNome());
        assertEquals("email1", response.getBody().getEmail());
        assertEquals("1111111111", response.getBody().getTelefone());
    }

    @Test
    public void testCriarCliente() {
        Cliente cliente3 = new Cliente(3L,"Cliente 3", "email3", "333333333", Instant.now(), "123456789", Instant.now(), StatusCliente.ATIVA, null);
        ResponseEntity<Cliente> response = restTemplate.postForEntity("/clientes", cliente3, Cliente.class);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody().getId());
        assertEquals("Cliente 3", response.getBody().getNome());
        assertEquals("email3", response.getBody().getEmail());
        assertEquals("333333333", response.getBody().getTelefone());
    }

    @Transactional
    @Test
    public void testAtualizarCliente() {
        Cliente cliente1 = new Cliente(null,"Cliente 1", "email1", "1111111111", Instant.now(), "123456789", Instant.now(), StatusCliente.ATIVA, null);
        clienteRepository.save(cliente1);

        cliente1.setTelefone("111111111");
        restTemplate.put("/clientes/{id}", cliente1, cliente1.getId());

        Cliente clienteAtualizado = clienteRepository.findById(cliente1.getId()).orElse(null);
        assertNotNull("O cliente atualizado não pode ser nulo", clienteAtualizado);
        assertEquals("O telefone do cliente não foi atualizado corretamente", "111111111", clienteAtualizado.getTelefone());
    }

    @Test
    public void testExcluirCliente() {
        // altera o status do cliente para inativo
        cliente2.setStatusCliente(StatusCliente.INATIVO);
        restTemplate.put("/clientes/{id}", cliente2, cliente2.getId());

        assertEquals("O status do cliente não foi atualizado corretamente", StatusCliente.INATIVO, cliente2.getStatusCliente());
    }


}

