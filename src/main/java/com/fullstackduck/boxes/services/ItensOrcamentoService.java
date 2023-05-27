package com.fullstackduck.boxes.services;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.fullstackduck.boxes.entities.ItensOrcamento;
import com.fullstackduck.boxes.repositories.ItensOrcamentoRepository;

@Service
public class ItensOrcamentoService {

    @Autowired
    private ItensOrcamentoRepository repository;

    @Async
    public CompletableFuture<List<ItensOrcamento>> findAll() {
        return CompletableFuture.completedFuture(repository.findAll());
    }

    @Async
    public CompletableFuture<ItensOrcamento> findById(Long id) {
        Optional<ItensOrcamento> obj = repository.findById(id);
        return CompletableFuture.completedFuture(obj.get());
    }

    @Async
    public CompletableFuture<ItensOrcamento> inserirItensOrcamento(ItensOrcamento obj) {
        return CompletableFuture.completedFuture(repository.save(obj));
    }
}
