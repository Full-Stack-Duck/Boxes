package com.fullstackduck.boxes.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.Instant;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import com.fullstackduck.boxes.entities.ItensOrcamento;
import com.fullstackduck.boxes.entities.Orcamento;
import com.fullstackduck.boxes.entities.enums.Status;
import com.fullstackduck.boxes.entities.enums.TipoEntrega;
import com.fullstackduck.boxes.repositories.OrcamentoRepository;
import com.fullstackduck.boxes.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@SpringBootTest
public class OrcamentoServiceTest {

    @Mock
    private OrcamentoRepository repository;

    @InjectMocks
    private OrcamentoService service;

    private final Orcamento orcamento = new Orcamento();

    @Test
    public void testFindAll() {
        Mockito.when(repository.findAll()).thenReturn(Arrays.asList(orcamento));

        List<Orcamento> orcamentos = service.findAll();

        assertEquals(1, orcamentos.size());
        assertEquals(orcamento, orcamentos.get(0));
    }

    @Test
    public void testFindById() {
        Mockito.when(repository.findById(orcamento.getId())).thenReturn(Optional.of(orcamento));

        Orcamento orcamentoEncontrado = service.findById(orcamento.getId());

        assertEquals(orcamento, orcamentoEncontrado);
    }

    @Test
    public void testFindByIdNotFound() {
        Mockito.when(repository.findById(orcamento.getId())).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            service.findById(orcamento.getId());
        });
    }

    @Test
    public void testInserirOrcamento() {
        Mockito.when(repository.save(Mockito.any(Orcamento.class))).thenReturn(orcamento);

        Orcamento orcamentoInserido = service.inserirOrcamento(orcamento);

        assertEquals(orcamento, orcamentoInserido);
    }

    @Test
    public void testAtualizarStatusOrcamento() {
        Orcamento orcamentoComStatus = new Orcamento(orcamento.getId(), TipoEntrega.ENTREGA,Instant.now(),Status.ATIVO,null,null);
        Mockito.when(repository.getReferenceById(orcamento.getId())).thenReturn(orcamento);
        Mockito.when(repository.save(Mockito.any(Orcamento.class))).thenReturn(orcamento);

        Orcamento orcamentoAtualizado = service.atualizarStatusOrcamento(orcamento.getId(), orcamentoComStatus);

        assertEquals(orcamentoComStatus, orcamentoAtualizado);
    }


    @Test
    public void testAtualizarStatusOrcamentoNotFound() {
        Mockito.when(repository.getReferenceById(orcamento.getId())).thenThrow(EntityNotFoundException.class);

        assertThrows(ResourceNotFoundException.class, () -> {
            service.atualizarStatusOrcamento(orcamento.getId(), orcamento);
        });
    }

    

    
    @Test
    public void testCalcularDesconto() {
        // Criando objeto Orcamento para teste
        Orcamento orcamento = new Orcamento();
        ItensOrcamento.setValorTotal(100.0);
        ItensOrcamento.setDesconto(50.0);

        double descontoEsperado = 50.0;
        double descontoCalculado = service.calcularDesconto(orcamento);

        assertEquals(descontoEsperado, descontoCalculado, 0.001);
    }


    @Test
    public void testCalcularDescontoValorAlto() {
        Orcamento orcamentoValorAlto = new Orcamento(1L, TipoEntrega.ENTREGA,Instant.now(),Status.ATIVO,null,null);
        double descontoEsperado = 1000.0;

        double descontoCalculado = service.calcularDesconto(orcamentoValorAlto);

        assertEquals(descontoEsperado, descontoCalculado);
    }

    @Test
    public void testCalcularDescontoTipoExpressa() {
        Orcamento orcamentoExpressa = new Orcamento(1L, TipoEntrega.ENTREGA,Instant.now(),Status.ATIVO,null,null);
        double descontoEsperado = 50.0;

        double descontoCalculado = service.calcularDesconto(orcamentoExpressa);

        assertEquals(descontoEsperado, descontoCalculado);
    }

    @Test
    public void testCalcularDescontoTipoNormal() {
        Orcamento orcamentoNormal = new Orcamento(1L, TipoEntrega.ENTREGA,Instant.now(),Status.ATIVO,null,null);
        double descontoEsperado = 25.0;

        double descontoCalculado = service.calcularDesconto(orcamentoNormal);

        assertEquals(descontoEsperado, descontoCalculado);
    }
}
        
        


