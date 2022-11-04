package com.vrtest.miniautorizador.exception;

public class SaldoInsuficienteException extends RuntimeException {

	private static final long serialVersionUID = -4889525038203093051L;

	public SaldoInsuficienteException(String message) {
		super(message);
	}
}
