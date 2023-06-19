package com.fullstackduck.boxes.security;

import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "*")
public record DadosTokenJWT(String token) {

}
