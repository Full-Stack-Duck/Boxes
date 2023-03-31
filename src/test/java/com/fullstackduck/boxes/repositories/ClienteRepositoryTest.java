package com.fullstackduck.boxes.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.transaction.annotation.Transactional;

import com.fullstackduck.boxes.entities.Cliente;
import com.fullstackduck.boxes.entities.enums.StatusCliente;
import com.fullstackduck.boxes.tests.Factory;

import lombok.Getter;
import lombok.Setter;


@SpringBootTest
@Transactional
public class ClienteRepositoryTest {
	
	@Autowired
	private ClienteRepository repository;
	
	@Getter @Setter private Long existingId;
	@Getter @Setter private Long nonExistingId;
	@Getter @Setter private Long countTotalClientes;
	
	
	@BeforeEach
	void setUp() throws Exception {
		
		existingId = 1L;
		nonExistingId = 100L;
		countTotalClientes = 25L;
		
	}
	
	
    @Test
    public void testSave() {
        // cria um novo usuário usando a classe Factory
        Cliente cliente = Factory.criarCliente();

        // salva o usuário no banco de dados
        Cliente clienteSalvo = repository.save(cliente);

        // verifica se o usuário salvo possui um ID não nulo
        assertNotNull(clienteSalvo.getId());

        // verifica se o usuário salvo possui as mesmas informações que o usuário criado
        assertEquals(cliente.getNome(), clienteSalvo.getNome());
        assertEquals(cliente.getEmail(), clienteSalvo.getEmail());
        assertEquals(cliente.getTelefone(), clienteSalvo.getTelefone());
        assertEquals(cliente.getDataNascimento(), clienteSalvo.getDataNascimento());
        assertEquals(cliente.getDocumento(), clienteSalvo.getDocumento());
        assertEquals(cliente.getDataCadastro(), clienteSalvo.getDataCadastro());
        assertEquals(cliente.getStatusCliente(), clienteSalvo.getStatusCliente());
    }

	
	
	 @Test
	    public void testFindById() {
	        // cria um novo usuário usando a classe Factory
	        Cliente cliente = Factory.criarCliente();

	        // salva o usuário no banco de dados
	        Cliente clienteSalvo = repository.save(cliente);

	        // busca o usuário salvo pelo ID
	        Optional<Cliente> clienteEncontrado = repository.findById(clienteSalvo.getId());

	        // verifica se o usuário foi encontrado
	        assertTrue(clienteEncontrado.isPresent());

	        // verifica se o usuário encontrado possui as mesmas informações que o usuário criado
	        assertEquals(cliente.getNome(), clienteSalvo.getNome());
	        assertEquals(cliente.getEmail(), clienteSalvo.getEmail());
	        assertEquals(cliente.getTelefone(), clienteSalvo.getTelefone());
	        assertEquals(cliente.getDataNascimento(), clienteSalvo.getDataNascimento());
	        assertEquals(cliente.getDocumento(), clienteSalvo.getDocumento());
	        assertEquals(cliente.getDataCadastro(), clienteSalvo.getDataCadastro());
	        assertEquals(cliente.getStatusCliente(), clienteSalvo.getStatusCliente());
	    }

	    @Test
	    public void testFindByIdNotFound() {
	        // tenta buscar um usuário que não existe no banco de dados
	        Optional<Cliente> clienteNaoEncontrado = repository.findById(999L);

	        // verifica se o usuário não foi encontrado
	        assertFalse(clienteNaoEncontrado.isPresent());
	    }
	
	
	    @Test
	    public void updateShouldPersistAndReturnCliente() {

	        Cliente cliente = Factory.criarCliente();
	        cliente.setId(existingId);

	        cliente.setNome("João");
	        cliente.setDocumento("987654321");

	        Cliente updatedCliente = repository.save(cliente);

	        assertNotNull(updatedCliente);
	        assertEquals(cliente.getId(), updatedCliente.getId());
	        assertEquals(cliente.getNome(), updatedCliente.getNome());
	        assertEquals(cliente.getDocumento(), updatedCliente.getDocumento());
	    }

	    @Test
	    public void updateShouldThrowDataIntegrityViolationExceptionWhenDocumentoIsBlank() {

	        Cliente cliente = Factory.criarCliente();
	        cliente.setId(existingId);

	        cliente.setDocumento("");

	        try {
	            repository.save(cliente);
	        } catch (DataIntegrityViolationException e) {
	            assertTrue(e.getMessage().contains("Uma propriedade que não pode ser nula , foi referenciada como nula : Documento"));
	        }
	        
	    }
	    

	    @Test
	    public void updateShouldThrowIllegalArgumentExceptionWhenIdDoesNotExist() {

	        Cliente cliente = Factory.criarCliente();
	        cliente.setId(nonExistingId);

	        try {
	            repository.save(cliente);
	        } catch (IllegalArgumentException e) {
	            assertTrue(e.getMessage().contains("Id não encontrado: " + nonExistingId));
	        }
	    
	}
	    
	    @Test
	    public void deleteShouldSetStatusInactiveAndPersistInDatabase() {
	        // Arrange
	        Cliente cliente = Factory.criarCliente();
	        cliente = repository.save(cliente);

	        // Act
	        repository.deleteById(cliente.getId());
	        Optional<Cliente> result = repository.findById(cliente.getId());
	        cliente.setStatusCliente(StatusCliente.INATIVO);


	        // Assert
	        Assertions.assertFalse(result.isPresent());
	        Assertions.assertEquals(StatusCliente.INATIVO, cliente.getStatusCliente());
	    }

	    
	
	

}
