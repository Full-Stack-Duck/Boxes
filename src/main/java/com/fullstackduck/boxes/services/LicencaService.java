package com.fullstackduck.boxes.services;

import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fullstackduck.boxes.entities.Licenca;
import com.fullstackduck.boxes.entities.Usuario;
import com.fullstackduck.boxes.entities.enums.StatusLicenca;
import com.fullstackduck.boxes.entities.enums.TipoLicenca;
import com.fullstackduck.boxes.repositories.LicencaRepository;
import com.fullstackduck.boxes.repositories.UsuarioRepository;
import com.fullstackduck.boxes.services.exceptions.ResourceNotFoundException;

@Service // Component registration
public class LicencaService {

	@Autowired
    private LicencaRepository licencaRepository;
	
	@Autowired
    private UsuarioRepository usuarioRepository;
	
	@Autowired
    public LicencaService(LicencaRepository licencaRepository) {
        this.licencaRepository = licencaRepository;
    }

    
    public List<Licenca> findAll() {
        return licencaRepository.findAll();
    }

    public Licenca findById(Long id) {
        System.out.println("ID recebido: " + id);
        try {
            Optional<Licenca> obj = licencaRepository.findById(id);
            return obj.orElseThrow(() -> new ResourceNotFoundException("Licença não encontrada com o id: " + id));
        } catch (NoSuchElementException ex) {
            throw new ResourceNotFoundException("Licença não encontrada com o id: " + id);
        }
    }





    public Instant dataValidade(Long id) {
        Licenca licenca = findById(id);
        if (licenca != null) {
            Instant dataValidade = licenca.getDataAquisicao().plus(Duration.ofDays(licenca.getDiasLicenca()));
            if (dataValidade.isAfter(Instant.now())) {
                return dataValidade;
            }
        }
        return null;
    }



    public long diasLicenca(Long id) {
        Licenca licenca = findById(id);
        return licenca.getDiasLicenca();
    }

    public Usuario renovarLicenca(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado"));
        TipoLicenca tipoLicenca = usuario.getTipoLicenca();
        Instant novaDataValidadeLicenca = Instant.now();
        if (tipoLicenca == TipoLicenca.GRATUITA) {
            novaDataValidadeLicenca = novaDataValidadeLicenca.plus(Duration.ofDays(15));
        } else if (tipoLicenca == TipoLicenca.MENSAL) {
            novaDataValidadeLicenca = novaDataValidadeLicenca.plus(Duration.ofDays(30));
        } else if (tipoLicenca == TipoLicenca.SEMESTRAL) {
            novaDataValidadeLicenca = novaDataValidadeLicenca.plus(Duration.ofDays(180));
        } else if (tipoLicenca == TipoLicenca.ANUAL) {
            novaDataValidadeLicenca = novaDataValidadeLicenca.plus(Duration.ofDays(365));
        }
        usuario.setDataValidadeLicenca(novaDataValidadeLicenca);
        return usuarioRepository.save(usuario);
    }

    public Licenca alterarLicenca(Long id, TipoLicenca tipoLicenca, Double valor) {
        Licenca licenca = findById(id);
        licenca.setTipoLicenca(tipoLicenca);
        licenca.setValor(valor);
        return licencaRepository.save(licenca);
    }

    public Licenca atualizarStatus(Long id, StatusLicenca statusLicenca) {
        Licenca licenca = findById(id);
        licenca.setStatusLicenca(statusLicenca);
        return licencaRepository.save(licenca);
	}

	public Licenca save(Licenca licenca) {
		return licencaRepository.save(licenca);
	}

	
	
		
	

}
