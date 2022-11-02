package com.vrtest.miniautorizador.service;

import com.vrtest.miniautorizador.command.CartaoCommand;

public interface CartaoService {

	public CartaoCommand cadastrarCartao(CartaoCommand model);
}
