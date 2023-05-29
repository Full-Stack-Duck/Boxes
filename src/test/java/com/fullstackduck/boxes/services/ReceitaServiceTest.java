package com.fullstackduck.boxes.services;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.time.Instant;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.fullstackduck.boxes.entities.Pagamento;
import com.fullstackduck.boxes.entities.enums.FormaPagamento;
import com.fullstackduck.boxes.entities.enums.StatusPagamento;
import com.fullstackduck.boxes.repositories.PagamentoRepository;
import com.fullstackduck.boxes.repositories.ReceitaRepository;

@SpringBootTest
public class ReceitaServiceTest {

	    @Autowired
	    private ReceitaService receitaService;

	    @MockBean
	    private ReceitaRepository receitaRepository;

	    @MockBean
	    private PagamentoRepository pagamentoRepository;

	    @Test
	    @DisplayName("Deve retornar o total de receitas")
	    public void testGetTotalReceita() throws Exception {
	    // Cria uma lista de pagamentos
	    List<Pagamento> pagamentos = Arrays.asList(
	    new Pagamento(1L, 100.0, Instant.now(), FormaPagamento.DEBITO, StatusPagamento.PAGO,null),
	    new Pagamento(2L, 200.0, Instant.now(), FormaPagamento.DEBITO, StatusPagamento.PAGO, null)
	    );
	 // Define o comportamento do pagamentoRepository.findAll() para retornar a lista de pagamentos criada acima
	    when(pagamentoRepository.findAll()).thenReturn(pagamentos);

	    // Chama o método getTotalReceita() do receitaService
	    CompletableFuture<Double> total = receitaService.getTotalReceita();

	    // Extrai o valor do CompletableFuture usando o método join()
	    double totalReceita = total.join();

	    // Verifica se o total retornado é igual a 300.0 (soma dos valores dos pagamentos)
	    assertEquals(300.0, totalReceita, 0.001);
	    }
	}
	
	
	
	

