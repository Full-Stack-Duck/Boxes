package com.fullstackduck.boxes.services;

import java.security.InvalidParameterException;
import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fullstackduck.boxes.entities.Licenca;
import com.fullstackduck.boxes.entities.enums.StatusLicenca;
import com.fullstackduck.boxes.entities.enums.TipoLicenca;
import com.fullstackduck.boxes.repositories.LicencaRepository;
import com.fullstackduck.boxes.services.exceptions.ResourceNotFoundException;

@Service //Registro de componente
public class LicencaService {

	@Autowired
	private LicencaRepository repository;
	
	public List<Licenca> findAll(){
		return repository.findAll();
	}
	
	public Licenca findById(Long id) {
		Optional<Licenca> obj = repository.findById(id);
		return obj.get();
	}
	
	public Instant dataValidade(Long id) {
	    Licenca licenca = repository.findById(id)
	            .orElseThrow(() -> new ResourceNotFoundException("Licença não encontrada"));
	    return licenca.getDataAquisicao().plus(Duration.ofDays(licenca.getDiasLicenca()));
	}


    public long diasLicenca(Long id) {
        Licenca licenca = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Licença não encontrada"));
        return licenca.getDiasLicenca();
    }

    public Licenca renovarLicenca(Long id, String plano) {
        Licenca licenca = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Licença não encontrada"));
        Instant novaDataValidade;
        if (plano.equalsIgnoreCase("gratuito")) {
            novaDataValidade = Instant.now().plus(Duration.ofDays(15));
        } else if (plano.equalsIgnoreCase("mensal")) {
            novaDataValidade = Instant.now().plus(Duration.ofDays(30));
        } else if (plano.equalsIgnoreCase("semestral")) {
            novaDataValidade = Instant.now().plus(Duration.ofDays(180));
        } else if (plano.equalsIgnoreCase("anual")) {
            novaDataValidade = Instant.now().plus(Duration.ofDays(365));
        } else {
            throw new InvalidParameterException("Plano de renovação inválido");
        }
        licenca.setDataAquisicao(Instant.now());
        licenca.setDataValidade(novaDataValidade);
        licenca.setDiasLicenca(Duration.between(Instant.now(), novaDataValidade).toDays());
        return repository.save(licenca);
    }


    public Licenca alterarLicenca(Long id, TipoLicenca tipoLicenca, Double valor) {
        Licenca licenca = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Licença não encontrada"));
        licenca.setTipoLicenca(tipoLicenca);
        licenca.setValor(valor);
        return repository.save(licenca);
    }

    public Licenca atualizarStatus(Long id, StatusLicenca statusLicenca) {
        Licenca licenca = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Licença não encontrada"));
        licenca.setStatusLicenca(statusLicenca);
        return repository.save(licenca);
    }
	
	

}
