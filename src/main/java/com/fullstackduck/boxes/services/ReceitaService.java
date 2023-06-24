package com.fullstackduck.boxes.services;

import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fullstackduck.boxes.entities.Pagamento;
import com.fullstackduck.boxes.entities.Receita;
import com.fullstackduck.boxes.entities.Usuario;
import com.fullstackduck.boxes.entities.enums.StatusPagamento;
import com.fullstackduck.boxes.repositories.PagamentoRepository;
import com.fullstackduck.boxes.repositories.ReceitaRepository;
import com.fullstackduck.boxes.repositories.UsuarioRepository;
import com.fullstackduck.boxes.services.exceptions.ResourceNotFoundException;

@Service //Registro de componente
public class ReceitaService {

	@Autowired
	private ReceitaRepository receitaRepository;
	
	@Autowired
    private PagamentoRepository pagamentoRepository;
	
	@Autowired
    private UsuarioRepository usuarioRepository;
	
	public List<Receita> findAll(){
		return receitaRepository.findAll();
	}
	
	public Receita findById(Long id) {
		Optional<Receita> obj = receitaRepository.findById(id);
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
	
	@Transactional
	public List<Receita> listarReceitas(Long idUsuario) {
        Usuario usuario = usuarioRepository.findById(idUsuario).orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado com o id: " + idUsuario));
        return usuario.getReceitas();
    }
	
	@Transactional
	public List<Receita> listarReceitasPeriodo(String dataInicio, String dataFim) {
		DateTimeFormatter formatter = DateTimeFormatter.ISO_INSTANT;
		Instant data1 = Instant.from(formatter.parse(dataInicio));
		Instant data2 = Instant.from(formatter.parse(dataFim));
	    return receitaRepository.findByDataReceitaBetween(data1, data2);
	}
	
	@Transactional
	public Double totalReceitasPeriodo(Long usuarioId, String dataInicio, String dataFim) {
		Usuario user = usuarioRepository.findById(usuarioId).orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado com o id: " + usuarioId));
		DateTimeFormatter formatter = DateTimeFormatter.ISO_INSTANT;
		Instant data1 = Instant.from(formatter.parse(dataInicio));
		Instant data2 = Instant.from(formatter.parse(dataFim));
		List<Receita> totalpd = receitaRepository.findByDataReceitaBetween(data1, data2);
		Double total = 0.0;
		for (Receita i : totalpd) {
			if (i.getUsuario().equals(user)) {
				if (i.getPagamento().getStatusPagamento() == StatusPagamento.PAGO) {
					total += i.getPagamento().getValor();
				}
			}
		}
	    return total;
	}
}
