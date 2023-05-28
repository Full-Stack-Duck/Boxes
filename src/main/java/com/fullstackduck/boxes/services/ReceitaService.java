package com.fullstackduck.boxes.services;

import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fullstackduck.boxes.entities.Pagamento;
import com.fullstackduck.boxes.entities.Receita;
import com.fullstackduck.boxes.entities.Usuario;
import com.fullstackduck.boxes.repositories.PagamentoRepository;
import com.fullstackduck.boxes.repositories.ReceitaRepository;
import com.fullstackduck.boxes.repositories.UsuarioRepository;

@Service
public class ReceitaService {

    @Autowired
    private ReceitaRepository receitaRepository;

    @Autowired
    private PagamentoRepository pagamentoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Async
    public CompletableFuture<List<Receita>> findAll() {
        List<Receita> receitas = receitaRepository.findAll();
        return CompletableFuture.completedFuture(receitas);
    }

    @Async
    public CompletableFuture<Receita> findById(Long id) {
        Optional<Receita> receita = receitaRepository.findById(id);
        return CompletableFuture.completedFuture(receita.orElse(null));
    }

    @Async
    public CompletableFuture<Double> getTotalReceita() {
        double total = 0;
        List<Pagamento> pagamentos = pagamentoRepository.findAll();
        for (Pagamento pagamento : pagamentos) {
            total += pagamento.getValor();
        }
        return CompletableFuture.completedFuture(total);
    }

    @Transactional
    @Async
    public CompletableFuture<List<Receita>> listarReceitas(Long idUsuario) {
        Usuario usuario = usuarioRepository.getReferenceById(idUsuario);
        List<Receita> receitas = usuario.getReceitas();
        return CompletableFuture.completedFuture(receitas);
    }

    @Transactional
    @Async
    public CompletableFuture<List<Receita>> listarReceitasPeriodo(String dataInicio, String dataFim) {
        DateTimeFormatter formatter = DateTimeFormatter.ISO_INSTANT;
        Instant data1 = Instant.from(formatter.parse(dataInicio));
        Instant data2 = Instant.from(formatter.parse(dataFim));
        List<Receita> receitas = receitaRepository.findByDataReceitaBetween(data1, data2);
        return CompletableFuture.completedFuture(receitas);
    }
}
