package com.fullstackduck.boxes.entities.enums;

public enum Status {

	//Atribuidos valores numéricos para cada enum
		ATIVO(1),
		INATIVO(2);
		
		//Atributo para o código do enum
		private int code;
		
		//Construtor do enum
		private Status(int code) {
			this.code = code;
		}
		
		//Método para tornar o código do enum acessível em outras classes
		public int getCode() {
			return code;
		}
		
		//Método para converter o código para o enum correspondente 
		public static Status valueOf(int code) {
			for(Status value : Status.values()) {
				if(value.getCode() == code) {
					return value;
			}
		}
		throw new IllegalArgumentException("Código do tipo de licença inválido");
	}

		public Object getStatus() {
			// TODO Auto-generated method stub
			return null;
		}
}

