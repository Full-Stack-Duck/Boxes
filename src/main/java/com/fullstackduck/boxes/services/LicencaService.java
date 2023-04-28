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
import com.fullstackduck.boxes.entities.enums.TipoLicenca;
import com.fullstackduck.boxes.repositories.LicencaRepository;
import com.fullstackduck.boxes.repositories.UsuarioRepository;
import com.fullstackduck.boxes.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service // Component registration
public class LicencaService {

	@Autowired
    private LicencaRepository licencaRepository;
	
	@Autowired
    private UsuarioRepository usuarioRepository;
    
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
    
    public Licenca inserirLicenca(Licenca licenca, Integer id) {
    	Usuario usuario = usuarioRepository.getReferenceById(id);
    	licenca.setDataAquisicao(Instant.now());
    	licenca.setDataValidade(licenca.getDataAquisicao());
    	licenca.setDiasLicenca();
    	licenca.setUsuario(usuario);
		return licencaRepository.save(licenca);
	}
    
    public Licenca atualizarStatusLicenca(Long id, Licenca obj) {
    	try {
			Licenca entity = licencaRepository.getReferenceById(id);
			atualizarStatusLicenca(entity, obj);
			return licencaRepository.save(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}
    
    public Licenca renovarLicenca(Long id, Licenca obj) {
    	try {
			Licenca entity = licencaRepository.getReferenceById(id);
			renovarLicenca(entity, obj);
			return licencaRepository.save(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
    }
    
    public Licenca alterarLicenca(Licenca licenca, Integer usuarioId) {
    	Usuario usuario = usuarioRepository.getReferenceById(usuarioId);
    	Licenca antigaLicenca = usuario.getLicenca();
    	Instant novaDataValidadeLicenca = antigaLicenca.getDataValidade();
        Integer novoDiasLicenca = antigaLicenca.getDiasLicenca();
        licenca.setDataAquisicao(Instant.now());
        TipoLicenca tipoLicenca = licenca.getTipoLicenca();
        licenca.setTipoLicenca(tipoLicenca);
        if (tipoLicenca == TipoLicenca.MENSAL) {
            novaDataValidadeLicenca = novaDataValidadeLicenca.plus(Duration.ofDays(30));
            novoDiasLicenca += 30; 
        } else if (tipoLicenca == TipoLicenca.SEMESTRAL) {
            novaDataValidadeLicenca = novaDataValidadeLicenca.plus(Duration.ofDays(180));
            novoDiasLicenca += 180;
        } else if (tipoLicenca == TipoLicenca.ANUAL) {
            novaDataValidadeLicenca = novaDataValidadeLicenca.plus(Duration.ofDays(365));
            novoDiasLicenca += 365;
        }
        licenca.setDataValidade(novaDataValidadeLicenca);
        licenca.setDiasLicenca();
    	licenca.setUsuario(usuario);
		return licencaRepository.save(licenca);
    }

	
	//métodos auxiliares
    private void atualizarStatusLicenca(Licenca entity, Licenca obj) {
		entity.setStatusLicenca(obj.getStatusLicenca());
	}
    
	private void renovarLicenca(Licenca entity, Licenca obj) {
        TipoLicenca tipoLicenca = entity.getTipoLicenca();
        Instant novaDataValidadeLicenca = entity.getDataValidade();
        Integer novoDiasLicenca = entity.getDiasLicenca();
        if (tipoLicenca == TipoLicenca.MENSAL) {
            novaDataValidadeLicenca = novaDataValidadeLicenca.plus(Duration.ofDays(30));
            novoDiasLicenca += 30; 
        } else if (tipoLicenca == TipoLicenca.SEMESTRAL) {
            novaDataValidadeLicenca = novaDataValidadeLicenca.plus(Duration.ofDays(180));
            novoDiasLicenca += 180;
        } else if (tipoLicenca == TipoLicenca.ANUAL) {
            novaDataValidadeLicenca = novaDataValidadeLicenca.plus(Duration.ofDays(365));
            novoDiasLicenca += 365;
        }
        entity.setDataValidade(novaDataValidadeLicenca);
        entity.setDiasLicenca();
    }
}
