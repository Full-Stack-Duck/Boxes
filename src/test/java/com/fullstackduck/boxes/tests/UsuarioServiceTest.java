package com.fullstackduck.boxes.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.lenient;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.fullstackduck.boxes.entities.Usuario;
import com.fullstackduck.boxes.entities.enums.Status;
import com.fullstackduck.boxes.repositories.UsuarioRepository;
import com.fullstackduck.boxes.services.UsuarioService;

@ExtendWith(MockitoExtension.class)
public class UsuarioServiceTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private UsuarioService usuarioService;

    private Usuario usuarioExistente;

    @BeforeEach
    public void setUp() {
        usuarioExistente = new Usuario();
        usuarioExistente.setNome("Usuario Teste");
        usuarioExistente.setEmail("usuario@teste.com");
        usuarioExistente.setStatus(Status.ATIVO);
    }

    @Test
    public void testCriarUsuario() {
        Usuario usuario = new Usuario();
        usuario.setNome("Novo Usuário");
        usuario.setEmail("novo@usuario.com");

        // Configura o mock do repository para retornar false quando chamado com o email do novo usuário
        lenient().when(usuarioRepository.existsByEmail("novo@usuario.com")).thenReturn(false);

        // Configura o mock do repository para retornar o usuário existente quando chamado com qualquer id
        lenient().when(usuarioRepository.findById(1L)).thenReturn(Optional.of(usuarioExistente));

        // Configura o mock do repository para retornar o usuário criado quando chamado com o usuário para salvar
        lenient().when(usuarioRepository.save(Mockito.any(Usuario.class))).thenReturn(usuario);


        // Chama o método criarUsuario
        Usuario novoUsuario = usuarioService.criarUsuario(usuario);

        // Verifica se o usuário criado é o mesmo que foi retornado pelo mock do repository
        assertEquals(usuario, novoUsuario);

        // Verifica se o status do usuário criado é ATIVO
        assertEquals(Status.ATIVO, novoUsuario.getStatus());
    }
}

