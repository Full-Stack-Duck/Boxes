package com.fullstackduck.boxes.entities.enums;

public enum StatusPagamentoPedido {

		//Atribuidos valores numéricos para cada enum
		AGUARDANDO_PAGAMENTO(1),
		PARCIALMENTE_PAGO(2),
		INTEGRALMENTE_PAGO(3),
		CANCELADO(4);
		
		//Atributo para o código do enum
		private int code;
		
		//Construtor do enum
		private StatusPagamentoPedido(int code) {
			this.code = code;
		}
		
		//Método para tornar o código do enum acessível em outras classes
		public int getCode() {
			return code;
		}
		
		//Método para converter o código para o enum correspondente 
		public static StatusPagamentoPedido valueOf(int code) {
			for(StatusPagamentoPedido value : StatusPagamentoPedido.values()) {
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

