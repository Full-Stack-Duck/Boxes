package com.fullstackduck.boxes.entities.enums;

public enum StatusCliente {

	//Atribuidos valores numéricos para cada enum
	ATIVA(1),
	INATIVO(2);
		
	//Atributo para o código do enum
	private int code;
	
	//Construtor do enum
	private StatusCliente(int code) {
		this.code = code;
	}
	
	//Método para tornar o código do enum acessível em outras classes
	public int getCode() {
		return code;
	}
	
	//Método para converter o código para o enum correspondente  
	public static StatusCliente valueOf(int code) {
		for(StatusCliente value : StatusCliente.values()) {
			if(value.getCode() == code) {
				return value;
			}
		}
		throw new IllegalArgumentException("Código de status inválido");
	}
}
