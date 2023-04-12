package com.fullstackduck.boxes.entities.enums;

public enum TipoLicenca {

	//Atribuidos valores numéricos para cada enum
	GRATUITA(1),
	MENSAL(2),
	SEMESTRAL(3),
	ANUAL(4);
	
	//Atributo para o código do enum
	private int code;
	
	//Construtor do enum
	private TipoLicenca(int code) {
		this.code = code;
	}
	
	//Método para tornar o código do enum acessível em outras classes
	public int getCode() {
		return code;
	}
	
	//Método para converter o código para o enum correspondente  
	public static TipoLicenca valueOf(int code) {
		for(TipoLicenca value : TipoLicenca.values()) {
			if(value.getCode() == code) {
				return value;
			}
		}
		throw new IllegalArgumentException("Código do tipo de licença inválido");
	}
}
