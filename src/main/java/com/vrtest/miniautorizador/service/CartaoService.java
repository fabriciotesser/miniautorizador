package com.vrtest.miniautorizador.service;

import java.math.BigDecimal;

import com.vrtest.miniautorizador.command.CartaoCommand;
import com.vrtest.miniautorizador.domain.Cartao;
import com.vrtest.miniautorizador.exception.CartaoEncontradoException;
import com.vrtest.miniautorizador.exception.CartaoNaoEncontradoException;

public interface CartaoService {

	CartaoCommand cadastrarCartao(CartaoCommand model) throws CartaoEncontradoException;

	BigDecimal getSaldoCartao(String numeroCartao) throws CartaoNaoEncontradoException;

	Cartao one(String numeroCartao, String senha) throws CartaoNaoEncontradoException;
}
