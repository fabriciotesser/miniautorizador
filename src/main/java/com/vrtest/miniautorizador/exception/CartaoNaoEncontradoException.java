package com.vrtest.miniautorizador.exception;

public class CartaoNaoEncontradoException extends RuntimeException {

	private static final long serialVersionUID = -6981545658688622324L;

	public CartaoNaoEncontradoException(String message) {
		super(message);
	}
}
