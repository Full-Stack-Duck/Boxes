package com.fullstackduck.boxes.services.exceptions;

public class EstoqueInsuficienteException extends Exception {
	private static final long serialVersionUID = 1L;

	public EstoqueInsuficienteException(String message) {
        super(message);
    }

}
