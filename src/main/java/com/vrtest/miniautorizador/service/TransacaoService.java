package com.vrtest.miniautorizador.service;

import com.vrtest.miniautorizador.command.TransacaoCommand;
import com.vrtest.miniautorizador.exception.CartaoNaoEncontradoException;
import com.vrtest.miniautorizador.exception.SaldoInsuficienteException;

public interface TransacaoService {

	void gerarTransacao(TransacaoCommand command) throws SaldoInsuficienteException, CartaoNaoEncontradoException;
}
