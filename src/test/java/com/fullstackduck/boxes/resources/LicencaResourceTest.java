package com.fullstackduck.boxes.resources;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.fullstackduck.boxes.entities.Despesa;
import com.fullstackduck.boxes.entities.enums.Categoria;
import com.fullstackduck.boxes.entities.enums.Status;
import com.fullstackduck.boxes.repositories.DespesaRepository;

import jakarta.persistence.EntityManager;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class LicencaResourceTest {

    @Autowired
    private TestRestTemplate restTemplate;
    
    @Autowired
    private EntityManager entityManager;

    @Autowired
    private DespesaRepository repository;

    private Despesa despesa1;
    private Despesa despesa2;

    @Before
    public void setUp() {
        Instant dataDespesa = Instant.now();
        despesa1 = new Despesa(null, "Despesa 1", Categoria.FIXA, 100.0, "Observação 1", dataDespesa, Status.ATIVO, null);
        despesa2 = new Despesa(null, "Despesa 2", Categoria.VARIAVEL, 200.0, "Observação 2", dataDespesa, Status.INATIVO, null);
        repository.save(despesa1);
        repository.save(despesa2);
    }

    @Test
    public void testListarDespesas() {
        ResponseEntity<String> response = restTemplate.getForEntity("/despesas", String.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody().contains("Despesa 1"));
        assertTrue(response.getBody().contains("Despesa 2"));
    }

    @Test
    public void testBuscarDespesaPorId() {
        ResponseEntity<Despesa> response = restTemplate.getForEntity("/despesas/{id}", Despesa.class, despesa1.getId());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Despesa 1", response.getBody().getNome());
        assertEquals(Categoria.FIXA, response.getBody().getCategoria());
        assertEquals(100.0, response.getBody().getValor(), 0.0);
    }

    @Test
    public void testCriarDespesa() {
        Instant dataDespesa = Instant.now();
        Despesa despesa3 = new Despesa(null, "Despesa 3", Categoria.VARIAVEL, 50.0, "Observação 3", dataDespesa, Status.ATIVO, null);
        ResponseEntity<Despesa> response = restTemplate.postForEntity("/despesas", despesa3, Despesa.class);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody().getId());
        assertEquals("Despesa 3", response.getBody().getNome());
        assertEquals(Categoria.VARIAVEL, response.getBody().getCategoria());
        assertEquals(50.0, response.getBody().getValor(), 0.0);
        assertEquals(dataDespesa.truncatedTo(ChronoUnit.SECONDS), response.getBody().getDataDespesa().truncatedTo(ChronoUnit.SECONDS));
    }


    @Transactional
    @Test
    public void testAtualizarDespesa() {
        Despesa despesa1 = new Despesa(null, "Despesa 1", Categoria.FIXA, 100.0, "Observação 1", Instant.now(), Status.ATIVO, null);
        entityManager.persist(despesa1);

        despesa1.setValor(150.0);
        restTemplate.put("/despesas/{id}", despesa1, despesa1.getId());

        Despesa despesaAtualizada = repository.findById(despesa1.getId()).orElse(null);
        assertNotNull("A despesa atualizada não pode ser nula", despesaAtualizada);
        assertEquals("O valor da despesa não foi atualizado corretamente", 150.0, despesaAtualizada.getValor(), 0.0);
    }



    @Test
    public void testExcluirDespesa() {
        restTemplate.delete("/despesas/{id}", despesa2.getId());
        Despesa despesaExcluida = repository.findById(despesa2.getId()).orElse(null);
        assertNull(despesaExcluida);
    }

}

