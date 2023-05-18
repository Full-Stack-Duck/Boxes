package com.fullstackduck.boxes.entities.enums;

public enum StatusPedido {

	//Atribuidos valores numéricos para cada enum
		EM_FILA_PREPARACAO(4),
		EM_PREPARACAO(5),
		EM_FILA_ENTREGA(6),
		EM_TRANSITO(7),
		ENTREGUE(8),
		CANCELADO(9);
		
		//Atributo para o código do enum
		private int code;
		
		//Construtor do enum
		private StatusPedido(int code) {
			this.code = code;
		}
		
		//Método para tornar o código do enum acessível em outras classes
		public int getCode() {
			return code;
		}
		
		//Método para converter o código para o enum correspondente  
		public static StatusPedido valueOf(int code) {
			for(StatusPedido value : StatusPedido.values()) {
				if(value.getCode() == code) {
					return value;
				}
			}
			throw new IllegalArgumentException("Código de status de licença inválido");
		}
}
