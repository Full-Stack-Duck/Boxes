package com.fullstackduck.boxes.entities.enums;

public enum TipoProduto {

	//Atribuidos valores numéricos para cada enum
		FRITO(1),
		FORNO(2),
		PRATO(3),
		BEBIDA(4),
		SOBREMESA(5),
		DOCE(6),
		ACOMPANHAMENTO(7),
		BEBIDA_NE(8),
		BOLOS_E_TORTAS(9);
		
		//Atributo para o código do enum
		private int code;
		
		//Construtor do enum
		private TipoProduto(int code) {
			this.code = code;
		}
		
		//Método para tornar o código do enum acessível em outras classes
		public int getCode() {
			return code;
		}
		
		//Método para converter o código para o enum correspondente  
		public static TipoProduto valueOf(int code) {
			for(TipoProduto value : TipoProduto.values()) {
				if(value.getCode() == code) {
					return value;
				}
			}
			throw new IllegalArgumentException("Código do tipo de licença inválido");
		}
}
