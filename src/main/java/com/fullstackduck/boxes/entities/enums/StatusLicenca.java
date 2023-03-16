package com.fullstackduck.boxes.entities.enums;

public enum StatusLicenca {

	//Atribuidos valores numéricos para cada enum
	ATIVA(1),
	EXPIRADA(2),
	CANCELADA(3);
	
	//Atributo para o código do enum
	private int code;
	
	//Construtor do enum
	private StatusLicenca(int code) {
		this.code = code;
	}
	
	//Método para tornar o código do enum acessível em outras classes
	public int getCode() {
		return code;
	}
	
	//Método para converter o código para o enum correspondente  
	public static StatusLicenca valueOf(int code) {
		for(StatusLicenca value : StatusLicenca.values()) {
			if(value.getCode() == code) {
				return value;
			}
		}
		throw new IllegalArgumentException("Código de status de licença inválido");
	}
}
