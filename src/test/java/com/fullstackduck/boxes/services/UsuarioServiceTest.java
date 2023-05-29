package com.fullstackduck.boxes.services;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
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
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.fullstackduck.boxes.entities.Cliente;
import com.fullstackduck.boxes.entities.Usuario;
import com.fullstackduck.boxes.entities.enums.Status;
import com.fullstackduck.boxes.repositories.UsuarioRepository;
import com.fullstackduck.boxes.services.exceptions.ResourceNotFoundException;

@SpringBootTest
public class UsuarioServiceTest {

    @Mock
    private UsuarioRepository repository;

    @InjectMocks
    private UsuarioService service;
    
    @InjectMocks
    private BCryptPasswordEncoder passwordEncoder;

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
    public void testFindById() throws Exception {
        Long id = 1L;
        Usuario usuario = new Usuario(id, "João", "12345678901", "joao@example.com", "123456", "11999999999", "Rua A, 123", "logo1.png", null, Status.ATIVO);

        when(repository.findById(id)).thenReturn(Optional.of(usuario));

        CompletableFuture<Usuario> result = service.findById(id);

        Usuario retrievedUsuario = result.get();

        Assertions.assertEquals(id, retrievedUsuario.getId());
        Assertions.assertEquals("João", retrievedUsuario.getNome());
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
    public void testInserirUsuario() throws Exception {
    Usuario usuario = new Usuario(1L, "João", "12345678901", "joao@example.com", "123456", "11999999999", "Rua A, 123", "logo1.png", null, Status.ATIVO);
    when(repository.save(any(Usuario.class))).thenReturn(usuario);

    CompletableFuture<Usuario> result = service.inserirUsuario(usuario);

    Usuario insertedUsuario = result.get();

    Assertions.assertEquals(1L, insertedUsuario.getId());
    Assertions.assertEquals("João", insertedUsuario.getNome());
    }
    
    

    @Test
    public void testValidarSenha() throws Exception {
        String email = "fulano@teste.com";
        String senha = "senha123";
        Usuario usuario = new Usuario(null, "Fulano", "11122233344", email, "12345678", senha, "Rua A", "logo.jpg", Instant.now(), Status.ATIVO);
        Mockito.when(repository.findByEmail(email)).thenReturn(usuario);

        CompletableFuture<Boolean> senhaValida = service.validarSenha(email, senha);
        assertTrue(senhaValida.get());

        CompletableFuture<Boolean> senhaInvalida = service.validarSenha(email, "senha456");
        assertFalse(senhaInvalida.get());
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
    public void testListarClientes() throws Exception {
        // Cria um usuário com clientes vinculados
        Cliente c1 = new Cliente(null, "Cliente 1", null, null, null, null, null, null, null);
        Cliente c2 = new Cliente(null, "Cliente 2", null, null, null, null, null, null, null);
        Usuario usuario = new Usuario(null, "Usuário de Teste", null, null, null, null, null, null, null, null);
        usuario.getClientes().addAll(Arrays.asList(c1, c2));

        // Mock da resposta do repositório
        Mockito.when(repository.findById(1L)).thenReturn(Optional.of(usuario));

        // Chama o método de listagem de clientes do service
        CompletableFuture<List<Cliente>> clientes = service.listarClientes(1L);

        // Verifica se a lista retornada contém os clientes vinculados ao usuário
        assertEquals(2, clientes.get().size());
        assertEquals("Cliente 1", clientes.get().get(0).getNome());
        assertEquals("Cliente 2", clientes.get().get(1).getNome());
    }

    
  

}
