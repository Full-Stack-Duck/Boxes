package com.fullstackduck.boxes.services;

import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fullstackduck.boxes.entities.Licenca;
import com.fullstackduck.boxes.entities.Usuario;
import com.fullstackduck.boxes.entities.enums.Status;
import com.fullstackduck.boxes.entities.enums.StatusLicenca;
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
    	licenca.calcularDiasLicenca();
    	licenca.setValor();
    	licenca.setUsuario(usuario);
		return licencaRepository.save(licenca);
	}
    
    @Transactional
    public Licenca atualizarStatusLicenca(Long id, Licenca obj) {
    	try {
			Licenca entity = licencaRepository.getReferenceById(id);
			atualizarStatusLicenca(entity, obj);
			return licencaRepository.save(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}
    
    @Transactional
    public Licenca renovarLicenca(Long id, Licenca obj) {
    	try {
			Licenca entity = licencaRepository.getReferenceById(id);
			renovarLicenca(entity, obj);
			return licencaRepository.save(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
    }
    
    @Transactional
    public Licenca alterarLicenca(Licenca licenca, Integer usuarioId) {
    	inserirLicenca(licenca, usuarioId);
    	Usuario usuario = usuarioRepository.getReferenceById(usuarioId);
    	Licenca antigaLicenca = usuario.findLicenca();
    	licenca.setDataAquisicao(antigaLicenca.getDataValidade());
    	Instant novaDataValidadeLicenca = antigaLicenca.getDataValidade();
        Integer novoDiasLicenca = antigaLicenca.getDiasLicenca();
        TipoLicenca tipoLicenca = licenca.getTipoLicenca();
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
        licenca.setStatusLicenca(StatusLicenca.ATIVA);
        licenca.setDataValidade(novaDataValidadeLicenca);
        licenca.calcularDiasLicenca();
        licenca.setValor();
    	licenca.setUsuario(usuario);
    	licenca.setDataAquisicao(Instant.now());
    	antigaLicenca.setStatusLicenca(StatusLicenca.CANCELADA);
    	licencaRepository.save(licenca);
    	licencaRepository.save(antigaLicenca);
		return licenca;
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
        entity.calcularDiasLicenca();
    }
}
