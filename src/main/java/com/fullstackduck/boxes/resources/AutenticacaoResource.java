package com.fullstackduck.boxes.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fullstackduck.boxes.entities.DadosAutenticacao;
import com.fullstackduck.boxes.entities.Usuario;
import com.fullstackduck.boxes.security.DadosTokenJWT;
import com.fullstackduck.boxes.security.TokenService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/login")
public class AutenticacaoResource {
	
	@Autowired
	private AuthenticationManager manager;
	
	@Autowired
	private TokenService tokenService;
	
	@PostMapping
	public ResponseEntity efetuarLogin(@RequestBody @Valid DadosAutenticacao dados) {
		var authenticationToken = new UsernamePasswordAuthenticationToken(dados.email(), dados.senha());
		var authentication = manager.authenticate(authenticationToken);
		
		var tokenJWT = tokenService.gerarToken((Usuario)authentication.getPrincipal());
		
		return ResponseEntity.ok(new DadosTokenJWT(tokenJWT));
	}

}
