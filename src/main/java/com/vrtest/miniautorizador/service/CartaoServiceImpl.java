package com.vrtest.miniautorizador.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vrtest.miniautorizador.command.CartaoCommand;
import com.vrtest.miniautorizador.domain.Cartao;
import com.vrtest.miniautorizador.exception.CartaoEncontradoException;
import com.vrtest.miniautorizador.repository.CartaoRepository;

@Service
public class CartaoServiceImpl implements CartaoService {

	@Autowired
	private CartaoRepository repository;

	@Override
	public CartaoCommand cadastrarCartao(CartaoCommand command) {
		validarCartao(command);
		Cartao cartao = new Cartao(command);
		cartao = this.repository.save(cartao);
		return command;
	}

	private void validarCartao(CartaoCommand command) {
		if(this.repository.findByNumeroCartaoAndSenha(command.getNumeroCartao(), command.getSenha()).isPresent())
			throw new CartaoEncontradoException();
	}
}
