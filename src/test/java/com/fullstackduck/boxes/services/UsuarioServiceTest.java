package com.fullstackduck.boxes.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.fullstackduck.boxes.entities.Cliente;
import com.fullstackduck.boxes.entities.Usuario;
import com.fullstackduck.boxes.entities.enums.Status;
import com.fullstackduck.boxes.repositories.UsuarioRepository;
import com.fullstackduck.boxes.services.exceptions.ResourceNotFoundException;

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

        CompletableFuture<List<Usuario>> result = service.findAll();

        Assertions.assertEquals(2, result.join().size());
        Assertions.assertEquals("João", result.join().get(0).getNome());
        Assertions.assertEquals("Maria", result.join().get(1).getNome());
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
    public void testListarClientes() {
        // Cria um usuário com clientes vinculados
        Cliente c1 = new Cliente(null, "Cliente 1", null, null, null, null, null, null, null);
        Cliente c2 = new Cliente(null, "Cliente 2", null, null, null, null, null, null, null);
        Usuario usuario = new Usuario(null, "Usuário de Teste", null, null, null, null, null, null, null, null);
        usuario.getClientes().addAll(Arrays.asList(c1, c2));

        // Mock da resposta do repositório
        when(repository.findById(1L)).thenReturn(Optional.of(usuario));

        // Chama o método de listagem de clientes do service
        List<Cliente> clientes = service.listarClientes(1L);

        // Verifica se a lista retornada contém os clientes vinculados ao usuário
        assertEquals(2, clientes.size());
        assertEquals("Cliente 1", clientes.get(0).getNome());
        assertEquals("Cliente 2", clientes.get(1).getNome());
    }

    
  

}
