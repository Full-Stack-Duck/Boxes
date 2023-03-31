package com.fullstackduck.boxes.repositories;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Instant;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.fullstackduck.boxes.entities.Despesa;
import com.fullstackduck.boxes.entities.enums.Categoria;
import com.fullstackduck.boxes.entities.enums.Status;

@RunWith(SpringRunner.class)
@DataJpaTest
public class DespesaRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private DespesaRepository repository;

    @Test
    public void testSalvarDespesa() {
        Instant dataDespesa = Instant.now();
        Despesa despesa = new Despesa(null, "Despesa Teste", Categoria.FIXA, 50.0, "Observação Teste", dataDespesa, Status.ATIVO, null);
        despesa = repository.save(despesa);
        assertNotNull(despesa.getId());
        assertEquals("Despesa Teste", despesa.getNome());
        assertEquals(Categoria.FIXA, despesa.getCategoria());
        assertEquals(50.0, despesa.getValor(), 0.0);
        assertEquals("Observação Teste", despesa.getObservacao());
        assertEquals(dataDespesa, despesa.getDataDespesa());
        assertEquals(Status.ATIVO, despesa.getStatus());
        assertNull(despesa.getUsuario());
    }

    @Test
    public void testListarDespesasPorPeriodo() {
        Instant dataInicio = Instant.parse("2023-03-01T00:00:00Z");
        Instant dataFim = Instant.parse("2023-03-31T23:59:59Z");
        Despesa despesa1 = new Despesa(null, "Despesa 1", Categoria.FIXA, 100.0, "Observação 1", dataInicio, null, null);
        Despesa despesa2 = new Despesa(null, "Despesa 2", Categoria.MATERIA_PRIMA, 200.0, "Observação 2", dataFim, null, null);
        Despesa despesa3 = new Despesa(null, "Despesa 3", Categoria.VARIAVEL, 50.0, "Observação 3", Instant.parse("2023-04-01T00:00:00Z"), null, null);
        entityManager.persist(despesa1);
        entityManager.persist(despesa2);
        entityManager.persist(despesa3);

        List<Despesa> resultado = repository.findByDataDespesaBetween(dataInicio, dataFim);

        assertNotNull(resultado);
        assertEquals(2, resultado.size());
        assertTrue(resultado.contains(despesa1));
        assertTrue(resultado.contains(despesa2));
    }
    
    @Test
    public void testExcluirDespesa() {
        Instant dataDespesa = Instant.now();
        Despesa despesa = new Despesa(null, "Despesa Teste", Categoria.FIXA, 50.0, "Observação Teste", dataDespesa, Status.ATIVO, null);
        despesa = repository.save(despesa);
        Long id = despesa.getId();
        repository.deleteById(id);
        assertFalse(repository.findById(id).isPresent());
    }
    
    @Test
    public void testAtualizarDespesa() {
        Instant dataDespesa = Instant.now();
        Despesa despesa = new Despesa(null, "Despesa Teste", Categoria.FIXA, 50.0, "Observação Teste", dataDespesa, Status.ATIVO, null);
        despesa = repository.save(despesa);
        Long id = despesa.getId();
        despesa.setNome("Despesa Teste Atualizada");
        despesa.setCategoria(Categoria.MATERIA_PRIMA);
        despesa.setValor(100.0);
        despesa.setObservacao("Observação Teste Atualizada");
        despesa.setStatus(Status.INATIVO);
        repository.save(despesa);
        Despesa despesaAtualizada = repository.findById(id).orElse(null);
        assertNotNull(despesaAtualizada);
        assertEquals("Despesa Teste Atualizada", despesaAtualizada.getNome());
        assertEquals(Categoria.MATERIA_PRIMA, despesaAtualizada.getCategoria());
        assertEquals(100.0, despesaAtualizada.getValor(), 0.0);
        assertEquals("Observação Teste Atualizada", despesaAtualizada.getObservacao());
        assertEquals(Status.INATIVO, despesaAtualizada.getStatus());
    }

    
    @Test
    public void testListarDespesasPorCategoria() {
        Instant dataDespesa = Instant.now();
        Despesa despesa1 = new Despesa(null, "Despesa 1", Categoria.FIXA, 100.0, "Observação 1", dataDespesa, null, null);
        Despesa despesa2 = new Despesa(null, "Despesa 2", Categoria.FIXA, 200.0, "Observação 2", dataDespesa, null, null);
        Despesa despesa3 = new Despesa(null, "Despesa 3", Categoria.VARIAVEL, 50.0, "Observação 3", dataDespesa, null, null);
        entityManager.persist(despesa1);
        entityManager.persist(despesa2);
        entityManager.persist(despesa3);

        List<Despesa> resultado = repository.findByCategoria(1); // 1 corresponde a Categoria.FIXA

        assertNotNull(resultado);
        assertEquals(2, resultado.size());
        assertTrue(resultado.contains(despesa1));
        assertTrue(resultado.contains(despesa2));
    }

    
    

}
