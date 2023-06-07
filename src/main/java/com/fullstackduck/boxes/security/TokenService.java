package com.fullstackduck.boxes.security;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.fullstackduck.boxes.entities.Usuario;

@Service
public class TokenService {
	
	@Value("${api.security.token.secret}")
	private String secret;
	
	public String gerarToken(Usuario usuario) {
		try {
		    var algoritmo = Algorithm.HMAC256(secret);
		    return JWT.create()
		        .withIssuer("API Boxes")
		        .withSubject(usuario.getEmail())
		        .withClaim("id", usuario.getId())
		        .withClaim("nome",usuario.getNome())
		        .withExpiresAt(dataExpiracao())
		        .sign(algoritmo);
		} catch (JWTCreationException exception){
		    throw new RuntimeException("erro ao gerar token jwt", exception); // Invalid Signing configuration / Couldn't convert Claims.
		}
	}

	private Instant dataExpiracao() {
		// TODO Auto-generated method stub
		return LocalDateTime.now().plusHours(1).toInstant(ZoneOffset.of("-03:00"));
	}
	
	
	public String getSubject(String tokenJWT) {
		try {
		    var algoritmo = Algorithm.HMAC256(secret);
		    return JWT.require(algoritmo)
		        .withIssuer("API Boxes")
		        .build()
		        .verify(tokenJWT)
		        .getSubject();
		      
		} catch (JWTVerificationException exception){
		     throw new RuntimeException("Token JWT inválido ou expirado!");
		  }
	}

}
