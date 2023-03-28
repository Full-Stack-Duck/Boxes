package com.fullstackduck.boxes.tests;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fullstackduck.boxes.entities.Usuario;
import com.fullstackduck.boxes.entities.enums.Status;
import com.fullstackduck.boxes.services.UsuarioService;
import com.fullstackduck.boxes.resources.UsuarioResource;

@WebMvcTest(UsuarioResource.class)
@AutoConfigureMockMvc(addFilters = false)
public class UsuarioResourceTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private UsuarioService service;

	@Autowired
	private ObjectMapper objectMapper;

	private Usuario usuario;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
		this.usuario = new Usuario(1L, "Fulano", "fulano@teste.com", "123456", "7512345678", "1111111", "Tomba", null, Status.ATIVO);
	}

	@Test
	public void testGetAllUsuarios() throws Exception {
		List<Usuario> usuarios = new ArrayList<Usuario>();
		usuarios.add(usuario);

		// mockando o comportamento do serviço
		org.mockito.BDDMockito.given(service.findAll()).willReturn(usuarios);

		MvcResult result = mockMvc.perform(get("/usuarios").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andReturn();

		String json = result.getResponse().getContentAsString();
		List<Usuario> usuariosResult = objectMapper.readValue(json, objectMapper.getTypeFactory().constructCollectionType(List.class, Usuario.class));

		assert (usuariosResult.size() == usuarios.size());
		assert (usuariosResult.get(0).getNome().equals(usuario.getNome()));
	}

	@Test
	public void testGetUsuarioById() throws Exception {
		org.mockito.BDDMockito.given(service.findById(usuario.getId())).willReturn(usuario);

		MvcResult result = mockMvc.perform(get("/usuarios/" + usuario.getId()).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andReturn();

		String json = result.getResponse().getContentAsString();
		Usuario usuarioResult = objectMapper.readValue(json, Usuario.class);

		assert (usuarioResult.getNome().equals(usuario.getNome()));
	}

	@Test
	public void testCreateUsuario() throws Exception {
		Usuario novoUsuario = new Usuario(1L, "Beltrano", "beltrano@teste.com", "123456", null, null, null, null, null);

		String json = objectMapper.writeValueAsString(novoUsuario);

		org.mockito.BDDMockito.given(service.inserirUsuario(novoUsuario)).willReturn(novoUsuario);

		mockMvc.perform(post("/usuarios").contentType(MediaType.APPLICATION_JSON).content(json)).andExpect(status().isCreated())
				.andExpect(header().string("Location", "http://localhost/usuarios/" + novoUsuario.getId()));
	}

}


