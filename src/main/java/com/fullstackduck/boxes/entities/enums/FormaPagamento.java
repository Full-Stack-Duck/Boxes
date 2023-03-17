package com.fullstackduck.boxes.entities.enums;

public enum FormaPagamento {

	//Atribuidos valores numéricos para cada enum
	DINHEIRO(1),
	PIX_TRANSFERENCIA(2),
	DEBITO(3),
	CREDITO(4);
	
	//Atributo para o código do enum
	private int code;
	
	//Construtor do enum
	private FormaPagamento(int code) {
		this.code = code;
	}
	
	//Método para tornar o código do enum acessível em outras classes
	public int getCode() {
		return code;
	}
	
	//Método para converter o código para o enum correspondente  
	public static FormaPagamento valueOf(int code) {
		for(FormaPagamento value : FormaPagamento.values()) {
			if(value.getCode() == code) {
				return value;
			}
		}
		throw new IllegalArgumentException("Código do tipo de licença inválido");
	}
}
