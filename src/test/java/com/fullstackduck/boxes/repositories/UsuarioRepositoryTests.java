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

import com.fullstackduck.boxes.entities.Usuario;
import com.fullstackduck.boxes.entities.enums.Status;
import com.fullstackduck.boxes.tests.Factory;

import lombok.Getter;
import lombok.Setter;


@SpringBootTest
@Transactional
public class UsuarioRepositoryTests {
	
	@Autowired
	private UsuarioRepository repository;
	
	@Getter @Setter private Long existingId;
	@Getter @Setter private Long nonExistingId;
	@Getter @Setter private Long countTotalUsuarios;
	
	
	@BeforeEach
	void setUp() throws Exception {
		
		existingId = 1L;
		nonExistingId = 100L;
		countTotalUsuarios = 25L;
		
	}
	
	
    @Test
    public void testSave() {
        // cria um novo usuário usando a classe Factory
        Usuario usuario = Factory.criarUsuario();

        // salva o usuário no banco de dados
        Usuario usuarioSalvo = repository.save(usuario);

        // verifica se o usuário salvo possui um ID não nulo
        assertNotNull(usuarioSalvo.getId());

        // verifica se o usuário salvo possui as mesmas informações que o usuário criado
        assertEquals(usuario.getNome(), usuarioSalvo.getNome());
        assertEquals(usuario.getDocumento(), usuarioSalvo.getDocumento());
        assertEquals(usuario.getEmail(), usuarioSalvo.getEmail());
        assertEquals(usuario.getTelefone(), usuarioSalvo.getTelefone());
        assertEquals(usuario.getSenha(), usuarioSalvo.getSenha());
        assertEquals(usuario.getEndereco(), usuarioSalvo.getEndereco());
        assertEquals(usuario.getStatus(), usuarioSalvo.getStatus());
    }

	
	
	 @Test
	    public void testFindById() {
	        // cria um novo usuário usando a classe Factory
	        Usuario usuario = Factory.criarUsuario();

	        // salva o usuário no banco de dados
	        Usuario usuarioSalvo = repository.save(usuario);

	        // busca o usuário salvo pelo ID
	        Optional<Usuario> usuarioEncontrado = repository.findById(usuarioSalvo.getId());

	        // verifica se o usuário foi encontrado
	        assertTrue(usuarioEncontrado.isPresent());

	        // verifica se o usuário encontrado possui as mesmas informações que o usuário criado
	        assertEquals(usuario.getNome(), usuarioSalvo.getNome());
	        assertEquals(usuario.getDocumento(), usuarioSalvo.getDocumento());
	        assertEquals(usuario.getEmail(), usuarioSalvo.getEmail());
	        assertEquals(usuario.getTelefone(), usuarioSalvo.getTelefone());
	        assertEquals(usuario.getSenha(), usuarioSalvo.getSenha());
	        assertEquals(usuario.getEndereco(), usuarioSalvo.getEndereco());
	        assertEquals(usuario.getStatus(), usuarioSalvo.getStatus());
	    }

	    @Test
	    public void testFindByIdNotFound() {
	        // tenta buscar um usuário que não existe no banco de dados
	        Optional<Usuario> usuarioNaoEncontrado = repository.findById(999L);

	        // verifica se o usuário não foi encontrado
	        assertFalse(usuarioNaoEncontrado.isPresent());
	    }
	
	
	    @Test
	    public void updateShouldPersistAndReturnUsuario() {

	        Usuario usuario = Factory.criarUsuario();
	        usuario.setId(existingId);

	        usuario.setNome("João");
	        usuario.setDocumento("987654321");

	        Usuario updatedUsuario = repository.save(usuario);

	        assertNotNull(updatedUsuario);
	        assertEquals(usuario.getId(), updatedUsuario.getId());
	        assertEquals(usuario.getNome(), updatedUsuario.getNome());
	        assertEquals(usuario.getDocumento(), updatedUsuario.getDocumento());
	    }

	    @Test
	    public void updateShouldThrowDataIntegrityViolationExceptionWhenDocumentoIsBlank() {

	        Usuario usuario = Factory.criarUsuario();
	        usuario.setId(existingId);

	        usuario.setDocumento("");

	        try {
	            repository.save(usuario);
	        } catch (DataIntegrityViolationException e) {
	            assertTrue(e.getMessage().contains("not-null property references a null or transient value : com.fullstackduck.boxes.entities.Usuario.cpf"));
	        }
	        
	    }
	    

	    @Test
	    public void updateShouldThrowIllegalArgumentExceptionWhenIdDoesNotExist() {

	        Usuario usuario = Factory.criarUsuario();
	        usuario.setId(nonExistingId);

	        try {
	            repository.save(usuario);
	        } catch (IllegalArgumentException e) {
	            assertTrue(e.getMessage().contains("Id not found: " + nonExistingId));
	        }
	    
	}
	    
	    @Test
	    public void deleteShouldSetStatusInactiveAndPersistInDatabase() {
	        // Arrange
	        Usuario usuario = Factory.criarUsuario();
	        usuario = repository.save(usuario);

	        // Act
	        repository.deleteById(usuario.getId());
	        Optional<Usuario> result = repository.findById(usuario.getId());
	        usuario.setStatus(Status.INATIVO);


	        // Assert
	        Assertions.assertFalse(result.isPresent());
	        Assertions.assertEquals(Status.INATIVO, usuario.getStatus());
	    }

	    
	
	

}
