package com.fullstackduck.boxes.tests;

import com.fullstackduck.boxes.entities.Usuario;
import com.fullstackduck.boxes.entities.enums.Status;

public class Factory {

	public static Usuario criarUsuario() {
		Usuario usuario = new Usuario(1L,"Lucas", "123456789","lucas@gmail.com","12345678","aaaaa","Tomba",null,Status.ATIVO);
		return usuario;
	}
}
