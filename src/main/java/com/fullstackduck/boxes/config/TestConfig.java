package com.fullstackduck.boxes.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.fullstackduck.boxes.entities.Cliente;
import com.fullstackduck.boxes.entities.Licenca;
import com.fullstackduck.boxes.entities.Usuario;
import com.fullstackduck.boxes.entities.enums.Status;
import com.fullstackduck.boxes.entities.enums.StatusCliente;
import com.fullstackduck.boxes.entities.enums.StatusLicenca;
import com.fullstackduck.boxes.entities.enums.TipoLicenca;
import com.fullstackduck.boxes.repositories.ClienteRepository;
import com.fullstackduck.boxes.repositories.LicencaRepository;
import com.fullstackduck.boxes.repositories.UsuarioRepository;

// Classe auxiliar de configuração para o perfil de testes
@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner{

	@Autowired
	private LicencaRepository licencaRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;

	@Override
	public void run(String... args) throws Exception {
		Licenca l1 = new Licenca(null, StatusLicenca.ATIVA, null, TipoLicenca.ANUAL, 98.90);
		
		licencaRepository.saveAll(Arrays.asList(l1));
		
		Usuario u1 = new Usuario(null, "Lucas", "123456789","lucas@gmail.com","7512345678", null, "Tomba", null,Status.ATIVO);
		
		usuarioRepository.saveAll(Arrays.asList(u1));
		
		Cliente c1 = new Cliente(null, "Vinicius","vinicius@gmail.com", "7512345678", null, "123456789", null,StatusCliente.ATIVA);
		
		clienteRepository.saveAll(Arrays.asList(c1));
	}
}
