package com.fullstackduck.boxes.services;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fullstackduck.boxes.entities.Pagamento;
import com.fullstackduck.boxes.entities.enums.StatusPagamento;
import com.fullstackduck.boxes.repositories.PagamentoRepository;
import com.fullstackduck.boxes.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class PagamentoService {

    @Autowired
    private PagamentoRepository repository;

    @Async
    public CompletableFuture<List<Pagamento>> findAll() {
        List<Pagamento> pagamentos = repository.findAll();
        return CompletableFuture.completedFuture(pagamentos);
    }

    @Async
    public CompletableFuture<Pagamento> findById(Long id) {
        return repository.findById(id)
                .map(CompletableFuture::completedFuture)
                .orElseThrow(() -> new ResourceNotFoundException(id));
    }

    @Transactional
    @Async
    public CompletableFuture<Pagamento> inserirPagamento(Pagamento obj) {
        return CompletableFuture.completedFuture(repository.save(obj));
    }

    @Transactional
    @Async
    public CompletableFuture<Pagamento> atualizarPagamento(Long id, Pagamento obj) {
        try {
            Pagamento entity = repository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException(id));
            atualizarDadosPagamento(entity, obj);
            return CompletableFuture.completedFuture(repository.save(entity));
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(id);
        }
    }

    @Transactional
    @Async
    public CompletableFuture<Pagamento> atualizarStatusPagamento(Long id, Pagamento obj) {
        try {
            Pagamento entity = repository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException(id));
            devolverPagamento(entity, obj);
            return CompletableFuture.completedFuture(repository.save(entity));
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(id);
        }
    }

    @Async
    private void atualizarDadosPagamento(Pagamento entity, Pagamento obj) {
        entity.setValor(obj.getValor());
        entity.setDataPagamento(obj.getDataPagamento());
        entity.setFormaPagamento(obj.getFormaPagamento());
        entity.setPedido(obj.getPedido());
    }

    @Async
    private void devolverPagamento(Pagamento entity, Pagamento obj) {
        entity.setStatusPagamento(StatusPagamento.DEVOLVIDO);
    }
}
