package com.fullstackduck.boxes.entities.enums;

public enum TipoArmazenamento {

	//Atribuidos valores numéricos para cada enum
		ESTOCAVEL(1),
		NAO_ESTOCAVEL(2);
		
		//Atributo para o código do enum
		private int code;
		
		//Construtor do enum
		private TipoArmazenamento(int code) {
			this.code = code;
		}
		
		//Método para tornar o código do enum acessível em outras classes
		public int getCode() {
			return code;
		}
		
		//Método para converter o código para o enum correspondente  
		public static TipoArmazenamento valueOf(int code) {
			for(TipoArmazenamento value : TipoArmazenamento.values()) {
				if(value.getCode() == code) {
					return value;
				}
			}
			throw new IllegalArgumentException("Código do tipo de licença inválido");
		}
}
