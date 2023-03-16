package com.fullstackduck.boxes.entities.enums;

public enum Categoria {

	//Atribuidos valores numéricos para cada enum
		FIXA(1),
		VARIAVEL(2),
		MATERIA_PRIMA(3),
		OUTROS(4);
		
		//Atributo para o código do enum
		private int code;
		
		//Construtor do enum
		private Categoria(int code) {
			this.code = code;
		}
		
		//Método para tornar o código do enum acessível em outras classes
		public int getCode() {
			return code;
		}
		
		//Método para converter o código para o enum correspondente  
		public static Categoria valueOf(int code) {
			for(Categoria value : Categoria.values()) {
				if(value.getCode() == code) {
					return value;
				}
			}
			throw new IllegalArgumentException("Código do tipo de licença inválido");
		}
}
