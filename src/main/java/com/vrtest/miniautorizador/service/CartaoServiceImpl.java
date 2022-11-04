package com.vrtest.miniautorizador.service;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vrtest.miniautorizador.command.CartaoCommand;
import com.vrtest.miniautorizador.domain.Cartao;
import com.vrtest.miniautorizador.domain.Transacao;
import com.vrtest.miniautorizador.exception.CartaoEncontradoException;
import com.vrtest.miniautorizador.exception.CartaoNaoEncontradoException;
import com.vrtest.miniautorizador.repository.CartaoRepository;

@Service
public class CartaoServiceImpl implements CartaoService {

	private static final String CARTAO_ENCONTRADO = "CARTAO_ENCONTRADO";
	private static final String CARTAO_INEXISTENTE = "CARTAO_INEXISTENTE";

	@Autowired
	private CartaoRepository repository;

	@Override
	public Cartao one(String numeroCartao, String senha) throws CartaoNaoEncontradoException {
		Cartao cartao = this.repository.findByNumeroCartaoAndSenha(numeroCartao, senha)
				.orElseThrow(() -> new CartaoNaoEncontradoException(CARTAO_INEXISTENTE)); 
		return cartao;
	}

	@Override
	public CartaoCommand cadastrarCartao(CartaoCommand command) throws CartaoEncontradoException {
		validarCartao(command);
		Cartao cartao = new Cartao(command);
		gerarTransacao(cartao);
		cartao = this.repository.save(cartao);
		return command;
	}

	private void validarCartao(CartaoCommand command) throws CartaoEncontradoException {
		if(this.repository.findByNumeroCartaoAndSenha(command.getNumeroCartao(), command.getSenha()).isPresent())
			throw new CartaoEncontradoException(CARTAO_ENCONTRADO);
	}

	private void gerarTransacao(Cartao cartao) {
		Transacao transacao = new Transacao(cartao, BigDecimal.valueOf(500));
		cartao.addTransacao(transacao);
	}

	@Override
	public BigDecimal getSaldoCartao(String numeroCartao) throws CartaoNaoEncontradoException {

		one(numeroCartao);
		BigDecimal saldo = this.repository.getSaldoCartao(numeroCartao);

		return saldo == null ? BigDecimal.ZERO : saldo;
	}

	private Cartao one(String numeroCartao) throws CartaoNaoEncontradoException {
		Cartao cartao = this.repository.findById(numeroCartao)
				.orElseThrow(() -> new CartaoNaoEncontradoException(CARTAO_INEXISTENTE)); 
		return cartao;
	}

}
