package com.fullstackduck.boxes.resources;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fullstackduck.boxes.entities.Cliente;
import com.fullstackduck.boxes.entities.Produto;
import com.fullstackduck.boxes.entities.Usuario;
import com.fullstackduck.boxes.entities.enums.Status;
import com.fullstackduck.boxes.services.UsuarioService;

@SpringJUnitConfig
@WebMvcTest(UsuarioResource.class)
@ContextConfiguration(classes = UsuarioResource.class)
@ActiveProfiles("test")
public class UsuarioResourceTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UsuarioService service;

    private ObjectMapper objectMapper = new ObjectMapper();

    private List<Usuario> usuarioList;

    private Usuario usuario;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        usuarioList = new ArrayList<>(Arrays.asList(
                new Usuario(1L, "nome","123456789","fulano@gmail.com", "12345689","senha123",null,null, Instant.now(), Status.ATIVO),
                new Usuario(2L, "outro","123456789","fulano@gmail.com", "12345689","outrasenha",null,null, Instant.now(), Status.ATIVO)
        ));
        usuario = new Usuario(1L, "nome","123456789","fulano@gmail.com", "12345689","senha123",null,null, Instant.now(), Status.ATIVO);
    }
 
    @Test
    public void testFindAll() throws Exception {
        when(service.findAll()).thenReturn(usuarioList);
        mockMvc.perform(MockMvcRequestBuilders.get("/usuarios")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    public void testFindById() throws Exception {
        when(service.findById(anyLong())).thenReturn(usuario);
        mockMvc.perform(MockMvcRequestBuilders.get("/usuarios/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.email").value("fulano@gmail.com"));
    }

    @Test
    public void testInserirUsuario() throws Exception {
        when(service.inserirUsuario(any(Usuario.class))).thenReturn(usuario);
        mockMvc.perform(MockMvcRequestBuilders.post("/usuarios")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(usuario)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(jsonPath("$.email").value("fulano@gmail.com"));
    }

    @Test
    public void testAtualizarStatusUsuario() throws Exception {
        when(service.atualizarStatusUsuario(anyLong(), any(Usuario.class))).thenReturn(usuario);
        mockMvc.perform(MockMvcRequestBuilders.put("/usuarios/{id}/attStatus", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(usuario)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.email").value("fulano@gmail.com"));
    }



   
    @Test
    public void testAtualizarUsuario() {
        // Criar um usuário
        Usuario usuario = new Usuario();
        usuario.setNome("João");
        usuario.setEmail("joao@teste.com");
        usuario.setSenha("123456");

        ResponseEntity<Usuario> responseUsuario = RestTemplate.postForEntity(BASE_PATH, usuario, Usuario.class);
        Usuario usuarioCriado = responseUsuario.getBody();

        // Atualizar o usuário
        usuarioCriado.setNome("João Silva");
        usuarioCriado.setSenha("654321");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Usuario> entity = new HttpEntity<>(usuarioCriado, headers);

        ResponseEntity<Usuario> responseEntity = RestTemplate.exchange(
                BASE_PATH + "/" + usuarioCriado.getId() + "/attUsuario",
                HttpMethod.PUT,
                entity,
                Usuario.class
        );

        // Verificar se o usuário foi atualizado
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        Usuario usuarioAtualizado = responseEntity.getBody();
        assertEquals(usuarioCriado.getId(), usuarioAtualizado.getId());
        assertEquals(usuarioCriado.getNome(), usuarioAtualizado.getNome());
        assertEquals(usuarioCriado.getSenha(), usuarioAtualizado.getSenha());

        // Desativar o usuário criado
        UsuarioService.atualizarStatus(usuarioCriado.getId());
    }

    @Test
    public void testValidarSenha() {
        // Criar um usuário
        Usuario usuario = new Usuario();
        usuario.setNome("Maria");
        usuario.setEmail("maria@teste.com");
        usuario.setSenha("123456");

        ResponseEntity<Usuario> responseUsuario = restTemplate.postForEntity(BASE_PATH, usuario, Usuario.class);
        Usuario usuarioCriado = responseUsuario.getBody();

        // Validar senha do usuário
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> entity = new HttpEntity<>("123456", headers);

        ResponseEntity<Boolean> responseEntity = restTemplate.exchange(
                BASE_PATH + "/" + usuarioCriado.getId() + "/validar-senha",
                HttpMethod.POST,
                entity,
                Boolean.class
        ); // Verificar se a senha foi validada
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertTrue(responseEntity.getBody());

        // Deletar o usuário criado
        usuarioService.deletarUsuario(usuarioCriado.getId());
    }
    
    @Test
    public void testRecuperarSenha() {
        String email = "test@test.com";
        ResponseEntity<Void> response = usuarioResource.recuperarSenha(email);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    public void testListarClientes() {
        Long id = 1L;
        List<Cliente> clientes = new ArrayList<>();
        Cliente cliente1 = new Cliente();
        cliente1.setId(1L);
        cliente1.setNome("Cliente 1");
        Cliente cliente2 = new Cliente();
        cliente2.setId(2L);
        cliente2.setNome("Cliente 2");
        clientes.add(cliente1);
        clientes.add(cliente2);
        when(usuarioService.listarClientes(id)).thenReturn(clientes);
        ResponseEntity<List<Cliente>> response = usuarioResource.listarClientes(id);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(clientes, response.getBody());
    }

    @Test
    public void testListarProdutos() {
        Long id = 1L;
        List<Produto> produtos = new ArrayList<>();
        Produto produto1 = new Produto();
        produto1.setId(1L);
        produto1.setNome("Produto 1");
        Produto produto2 = new Produto();
        produto2.setId(2L);
        produto2.setNome("Produto 2");
        produtos.add(produto1);
        produtos.add(produto2);
        when(usuarioService.listarProdutos(id)).thenReturn(produtos);
        ResponseEntity<List<Produto>> response = usuarioResource.listarProdutos(id);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(produtos, response.getBody());
    }
    
   

}