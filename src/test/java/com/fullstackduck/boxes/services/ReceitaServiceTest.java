package com.fullstackduck.boxes.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.fullstackduck.boxes.entities.Pagamento;
import com.fullstackduck.boxes.entities.Pedido;
import com.fullstackduck.boxes.entities.enums.FormaPagamento;
import com.fullstackduck.boxes.entities.enums.Status;
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
	    public void testGetTotalReceita() {
	        // Cria uma lista de pagamentos
	        List<Pagamento> pagamentos = Arrays.asList(
	            new Pagamento(1L, 100.0, Instant.now(), FormaPagamento.DEBITO, StatusPagamento.PAGO,null),
	            new Pagamento(2L, 200.0, Instant.now(), FormaPagamento.DEBITO, StatusPagamento.PAGO, null)
	        );
	        
	 	        
	        // Define o comportamento do pagamentoRepository.findAll() para retornar a lista de pagamentos criada acima
	        when(pagamentoRepository.findAll()).thenReturn(pagamentos);

	        // Chama o método getTotalReceita() do receitaService
	        double total = receitaService.getTotalReceita();

	        // Verifica se o total retornado é igual a 300.0 (soma dos valores dos pagamentos)
	        assertEquals(300.0, total, 0.001);
	    }
	}
	
	
	
	

