package com.fullstackduck.boxes.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fullstackduck.boxes.entities.Orcamento;
import com.fullstackduck.boxes.entities.Pagamento;
import com.fullstackduck.boxes.entities.Receita;
import com.fullstackduck.boxes.repositories.PagamentoRepository;
import com.fullstackduck.boxes.services.ReceitaService;

//Controlador Rest
@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/receitas")
public class ReceitaResource {

	@Autowired
	private ReceitaService service;
	
	private final PagamentoRepository pagamentoRepository;

    public ReceitaResource(PagamentoRepository pagamentoRepository) {
        this.pagamentoRepository = pagamentoRepository;
    }
	
	@GetMapping
	public ResponseEntity<List<Receita>> findAll(){
		List<Receita> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Receita> findById(@PathVariable Long id){
		Receita obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@GetMapping("/total")
    public ResponseEntity<Double> getTotalReceita() {
        Double totalReceita = pagamentoRepository.findAll().stream()
                .mapToDouble(Pagamento::getValor)
                .sum();

        return ResponseEntity.ok(totalReceita);
    }
	
	@GetMapping(value = "/{id}/Receita")
	public ResponseEntity<List<Receita>> listarReceita(@PathVariable Long id) {
	    List<Receita> receita = service.listarReceitas(id);
	    return ResponseEntity.ok().body(receita);
	}
	
	@GetMapping(value = "/{id}/Receitapd")
	public List<Receita> listarReceitaPeriodo(@PathVariable Long id,@RequestParam String dataInicio, @RequestParam String dataFim){
		List<Receita> receita = service.listarReceitasPeriodo(dataInicio, dataFim);
		return receita;
	}
}
