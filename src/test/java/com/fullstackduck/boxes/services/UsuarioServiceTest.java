package com.fullstackduck.boxes.services;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

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
        usuarios.add(new Usuario(1L, "João", "12345678901", "joao@example.com", "123456", "11999999999", "Rua A, 123", "logo1.png", Status.INATIVO));
        usuarios.add(new Usuario(2L, "Maria", "98765432101", "maria@example.com", "654321", "11999999998", "Rua B, 321", "logo2.png",Status.ATIVO));

        when(repository.findAll()).thenReturn(usuarios);

        List<Usuario> result = service.findAll();

        Assertions.assertEquals(2, result.size());
        Assertions.assertEquals("João", result.get(0).getNome());
        Assertions.assertEquals("Maria", result.get(1).getNome());
    }

    @Test
    public void testFindById() {
        Long id = 1L;
        Usuario usuario = new Usuario(id, "João", "12345678901", "joao@example.com", "123456", "11999999999", "Rua A, 123", "logo1.png", Status.ATIVO);

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
        Usuario usuario = new Usuario(1L, "João", "12345678901", "joao@example.com", "123456", "11999999999", "Rua A, 123", "logo1.png", Status.ATIVO);

        when(repository.save(any(Usuario.class))).thenReturn(usuario);

        Usuario result = service.inserirUsuario(usuario);

        Assertions.assertEquals(1L, result.getId());
        Assertions.assertEquals("João", result.getNome());
    }
    
   
   



}
