package com.fullstackduck.boxes.entities.enums;

public enum StatusPagamento {

	//Atribuidos valores numéricos para cada enum
		PAGO(1),
		DEVOLVIDO(2);
		
		//Atributo para o código do enum
		private int code;
		
		//Construtor do enum
		private StatusPagamento(int code) {
			this.code = code;
		}
		
		//Método para tornar o código do enum acessível em outras classes
		public int getCode() {
			return code;
		}
		
		//Método para converter o código para o enum correspondente 
		public static StatusPagamento valueOf(int code) {
			for(StatusPagamento value : StatusPagamento.values()) {
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

