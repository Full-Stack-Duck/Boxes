package com.fullstackduck.boxes.tests;

import com.fullstackduck.boxes.entities.Cliente;
import com.fullstackduck.boxes.entities.Usuario;
import com.fullstackduck.boxes.entities.enums.Status;
import com.fullstackduck.boxes.entities.enums.StatusCliente;

public class Factory {

	public static Usuario criarUsuario() {
		Usuario usuario = new Usuario(1L,"Lucas", "123456789","lucas@gmail.com","12345678","aaaaa","Tomba",null,Status.ATIVO);
		return usuario;
	}
	
	public static Cliente criarCliente() {
		Cliente cliente = new Cliente(1L, "Pedro", "teste@gmail.com",null, null, null,
				null, StatusCliente.ATIVA, null);
		return cliente;
	}
	
	
}
