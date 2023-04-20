package com.fullstackduck.boxes.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fullstackduck.boxes.entities.Pagamento;
import com.fullstackduck.boxes.entities.Receita;
import com.fullstackduck.boxes.repositories.PagamentoRepository;
import com.fullstackduck.boxes.repositories.ReceitaRepository;

@Service //Registro de componente
public class ReceitaService {

	@Autowired
	private ReceitaRepository repository;
	
	@Autowired
    private PagamentoRepository pagamentoRepository;
	
	public List<Receita> findAll(){
		return repository.findAll();
	}
	
	public Receita findById(Long id) {
		Optional<Receita> obj = repository.findById(id);
		return obj.get();
	}
	
	public double getTotalReceita() {
        double total = 0;
        List<Pagamento> pagamentos = pagamentoRepository.findAll();
        for (Pagamento pagamento : pagamentos) {
            total += pagamento.getValor();
        }
        return total;
    }
}
