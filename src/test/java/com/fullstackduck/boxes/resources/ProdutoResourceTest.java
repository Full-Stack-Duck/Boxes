package com.fullstackduck.boxes.resources;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.fullstackduck.boxes.entities.Produto;
import com.fullstackduck.boxes.entities.enums.Status;
import com.fullstackduck.boxes.entities.enums.TipoArmazenamento;
import com.fullstackduck.boxes.entities.enums.TipoProduto;
import com.fullstackduck.boxes.services.ProdutoService;

@SpringBootTest
@AutoConfigureMockMvc
public class ProdutoResourceTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProdutoService produtoService;
    
    @BeforeEach
    public void setUp() {
        Produto produto1 = new Produto(1L, "Produto 1", 50.0, TipoArmazenamento.ESTOCAVEL, 10, TipoProduto.BEBIDA, Status.ATIVO, "Sem observações", null, null);
        Produto produto2 = new Produto(2L, "Produto 2", 100.0, TipoArmazenamento.NAO_ESTOCAVEL, 20, TipoProduto.DOCE, Status.ATIVO, "Sem observações", null, null);
        
        List<Produto> produtos = Arrays.asList(produto1, produto2);
        
        Mockito.when(produtoService.findAll()).thenReturn(produtos);
    }

    @Test
    @DisplayName("Deve retornar lista de produtos")
    public void testFindAll() throws Exception {
    List<Produto> produtos = Arrays.asList(new Produto(1L, "Produto 1", 50.0, TipoArmazenamento.ESTOCAVEL, 10, TipoProduto.BEBIDA, Status.ATIVO, "Sem observações", null, null),
    new Produto(2L, "Produto 2", 100.0, TipoArmazenamento.NAO_ESTOCAVEL, 20, TipoProduto.DOCE, Status.ATIVO, "Sem observações", null, null));
    
    when(produtoService.findAll()).thenReturn(produtos);

    mockMvc.perform(get("/produtos"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$", hasSize(2)))
            .andExpect(jsonPath("$[0].id").value(1))
            .andExpect(jsonPath("$[0].nome").value("Produto 1"))
            .andExpect(jsonPath("$[1].id").value(2))
            .andExpect(jsonPath("$[1].nome").value("Produto 2"));
    }

    

}

