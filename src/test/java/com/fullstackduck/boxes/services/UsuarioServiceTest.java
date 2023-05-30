package com.fullstackduck.boxes.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.security.auth.login.LoginException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.fullstackduck.boxes.entities.Usuario;
import com.fullstackduck.boxes.entities.enums.Status;
import com.fullstackduck.boxes.repositories.UsuarioRepository;
import com.fullstackduck.boxes.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

public class UsuarioServiceTest {

    @Mock
    private UsuarioRepository repository;

    @InjectMocks
    private UsuarioService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindAll() {
        List<Usuario> usuarios = new ArrayList<>();
        usuarios.add(new Usuario(1L, "João", "12345678901", "joao@example.com", "123456", "11999999999", "Rua A, 123", "logo1.png", null, Status.INATIVO));
        usuarios.add(new Usuario(2L, "Maria", "98765432101", "maria@example.com", "654321", "11999999998", "Rua B, 321", "logo2.png",null, Status.ATIVO));

        when(repository.findAll()).thenReturn(usuarios);

        List<Usuario> result = service.findAll();

        Assertions.assertEquals(2, result.size());
        Assertions.assertEquals("João", result.get(0).getNome());
        Assertions.assertEquals("Maria", result.get(1).getNome());
    }

    @Test
    public void testFindById() {
        Long id = 1L;
        Usuario usuario = new Usuario(id, "João", "12345678901", "joao@example.com", "123456", "11999999999", "Rua A, 123", "logo1.png", null, Status.ATIVO);

        when(repository.findById(id)).thenReturn(Optional.of(usuario));

        Usuario result = service.findById(id);

        Assertions.assertEquals(id, result.getId());
        Assertions.assertEquals("João", result.getNome());
    }

    @Test
    public void testFindByIdNotFound() {
        Long id = 1L;

        when(repository.findById(id)).thenReturn(Optional.empty());

        Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            service.findById(id);
        });
    }

    @Test
    public void testInserirUsuario() {
        Usuario usuario = new Usuario(1L, "João", "12345678901", "joao@example.com", "123456", "11999999999", "Rua A, 123", "logo1.png", null, Status.ATIVO);

        when(repository.save(any(Usuario.class))).thenReturn(usuario);

        Usuario result = service.inserirUsuario(usuario);

        Assertions.assertEquals(1L, result.getId());
        Assertions.assertEquals("João", result.getNome());
    }
    
    @Test
    public void testValidarSenha() {
        String email = "fulano@teste.com";
        String senha = "senha123";
        Usuario usuario = new Usuario(null, "Fulano", "11122233344", email, "12345678",senha, "Rua A", "logo.jpg", Instant.now(), Status.ATIVO);
        Mockito.when(repository.findByEmail(email)).thenReturn(usuario);
        assertTrue(service.validarSenha(email, senha));
        assertFalse(service.validarSenha(email, "senha456"));
    }
    
   

    @Test
    public void testRecuperarSenha() {
        String email = "fulano@teste.com";
        String senha = "senha123";
        Usuario usuario = new Usuario(null, "Fulano", "11122233344", email, "12345678",senha, "Rua A", "logo.jpg", Instant.now(), Status.ATIVO);
        Mockito.when(repository.findByEmail(email)).thenReturn(usuario);
        assertEquals(senha, service.recuperarSenha(email));
        assertThrows(ResourceNotFoundException.class, () -> {
            service.recuperarSenha("email_invalido@teste.com");
        });
    }
    
    @Test
    public void testAtualizarStatusUsuario_ExistingUsuario_Success() {
        // Criação de um objeto Usuario existente para ser atualizado
        Long usuarioId = 1L;
        Usuario existingUsuario = new Usuario();
        existingUsuario.setId(usuarioId);
        existingUsuario.setStatus(Status.ATIVO);

        // Criação de um objeto Usuario com o status atualizado
        Usuario updatedUsuario = new Usuario();
        updatedUsuario.setStatus(Status.INATIVO);

        // Configuração do comportamento do mock do repository
        when(repository.getReferenceById(usuarioId)).thenReturn(existingUsuario);
        when(repository.save(existingUsuario)).thenReturn(existingUsuario);

        // Chamada do método a ser testado
        Usuario result = service.atualizarStatusUsuario(usuarioId, updatedUsuario);

        // Verificação do resultado
        assertNotNull(result);
        assertEquals(Status.INATIVO, result.getStatus());

        // Verificação de chamada do método getReferenceById e save no repository
        verify(repository, times(1)).getReferenceById(usuarioId);
        verify(repository, times(1)).save(existingUsuario);
    }

    @Test
    public void testAtualizarStatusUsuario_NonExistingUsuario_ExceptionThrown() {
        // Criação de um objeto Usuario não existente para ser atualizado
        Long nonExistingUsuarioId = 1L;
        Usuario updatedUsuario = new Usuario();
        updatedUsuario.setStatus(Status.INATIVO);

        // Configuração do comportamento do mock do repository
        when(repository.getReferenceById(nonExistingUsuarioId)).thenThrow(new EntityNotFoundException());

        // Chamada do método a ser testado e verificação de exceção
        assertThrows(ResourceNotFoundException.class, () -> {
            service.atualizarStatusUsuario(nonExistingUsuarioId, updatedUsuario);
        });

        // Verificação de chamada do método getReferenceById no repository
        verify(repository, times(1)).getReferenceById(nonExistingUsuarioId);
    }
    
    @Test
    public void testRecuperarSenha_ExistingUsuario_Success() {
        // Criação de um objeto Usuario existente para ser recuperada a senha
        String email = "teste@example.com";
        Usuario existingUsuario = new Usuario();
        existingUsuario.setEmail(email);
        existingUsuario.setSenha("senha123");

        // Configuração do comportamento do mock do repository
        when(repository.findByEmail(email)).thenReturn(existingUsuario);

        // Chamada do método a ser testado
        String senhaRecuperada = service.recuperarSenha(email);

        // Verificação do resultado
        assertNotNull(senhaRecuperada);
        assertEquals("senha123", senhaRecuperada);

        // Verificação de chamada do método findByEmail no repository
        verify(repository, times(1)).findByEmail(email);
    }

    @Test
    public void testRecuperarSenha_NonExistingUsuario_ExceptionThrown() {
        // Criação de um email de Usuario não existente para recuperar a senha
        String nonExistingEmail = "naoexiste@example.com";

        // Configuração do comportamento do mock do repository
        when(repository.findByEmail(nonExistingEmail)).thenReturn(null);

        // Chamada do método a ser testado e verificação de exceção
        assertThrows(ResourceNotFoundException.class, () -> {
            service.recuperarSenha(nonExistingEmail);
        });

        // Verificação de chamada do método findByEmail no repository
        verify(repository, times(1)).findByEmail(nonExistingEmail);
    }
    
    @Test
    public void testLogin_CorrectCredentials_Success() throws Exception {
        // Criação de um objeto Usuario existente para realizar o login
        String email = "teste@example.com";
        String senha = "senha123";
        Usuario existingUsuario = new Usuario();
        existingUsuario.setEmail(email);
        existingUsuario.setSenha(senha);

        // Configuração do comportamento do mock do repository
        when(repository.findByEmail(email)).thenReturn(existingUsuario);

        // Chamada do método a ser testado
        Usuario loggedUsuario = service.login(email, senha);

        // Verificação do resultado
        assertNotNull(loggedUsuario);
        assertEquals(existingUsuario, loggedUsuario);

        // Verificação de chamada do método findByEmail no repository
        verify(repository, times(1)).findByEmail(email);
    }

    @Test
    public void testLogin_IncorrectCredentials_ExceptionThrown() throws Exception {
        // Criação de um email e senha incorretos para realizar o login
        String email = "teste@example.com";
        String senha = "senha123";

        // Configuração do comportamento do mock do repository
        when(repository.findByEmail(email)).thenReturn(null);

        // Chamada do método a ser testado e verificação de exceção
        assertThrows(LoginException.class, () -> {
            service.login(email, senha);
        });

        // Verificação de chamada do método findByEmail no repository
        verify(repository, times(1)).findByEmail(email);
    }
    
  

}
