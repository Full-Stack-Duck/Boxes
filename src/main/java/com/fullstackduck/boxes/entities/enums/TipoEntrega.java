package com.fullstackduck.boxes.entities.enums;

public enum TipoEntrega {

	//Atribuidos valores numéricos para cada enum
			ENTREGA(1),
			RETIRADA(2);
			
			//Atributo para o código do enum
			private int code;
			
			//Construtor do enum
			private TipoEntrega(int code) {
				this.code = code;
			}
			
			//Método para tornar o código do enum acessível em outras classes
			public int getCode() {
				return code;
			}
			
			//Método para converter o código para o enum correspondente  
			public static TipoEntrega valueOf(int code) {
				for(TipoEntrega value : TipoEntrega.values()) {
					if(value.getCode() == code) {
						return value;
					}
				}
				throw new IllegalArgumentException("Código do tipo de licença inválido");
			}
}
