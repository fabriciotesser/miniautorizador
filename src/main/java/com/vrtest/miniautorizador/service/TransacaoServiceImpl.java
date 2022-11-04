package com.vrtest.miniautorizador.service;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vrtest.miniautorizador.command.TransacaoCommand;
import com.vrtest.miniautorizador.domain.Cartao;
import com.vrtest.miniautorizador.domain.Transacao;
import com.vrtest.miniautorizador.exception.CartaoNaoEncontradoException;
import com.vrtest.miniautorizador.exception.SaldoInsuficienteException;
import com.vrtest.miniautorizador.repository.TransacaoRepository;

@Service
public class TransacaoServiceImpl implements TransacaoService {

	private TransacaoRepository repository;
	private CartaoService cartaoService;

	@Autowired
	public TransacaoServiceImpl(TransacaoRepository repository, CartaoService cartaoService) {
		this.repository = repository;
		this.cartaoService = cartaoService;
	}

	@Override
	public void gerarTransacao(TransacaoCommand command) throws SaldoInsuficienteException, CartaoNaoEncontradoException {
		Cartao cartao = this.cartaoService.one(command.getNumeroCartao(), command.getSenhaCartao());
		validaSaldoCartao(cartao, command.getValor());
		Transacao transacao = new Transacao(cartao, command.getValor().multiply(BigDecimal.valueOf(-1)));
		this.repository.save(transacao);
	}

	private void validaSaldoCartao(Cartao cartao, BigDecimal valorTransacao) throws SaldoInsuficienteException, CartaoNaoEncontradoException {
		BigDecimal saldoCartao = this.cartaoService.getSaldoCartao(cartao.getNumeroCartao());
		saldoCartao = saldoCartao.subtract(valorTransacao);
		if(saldoCartao.compareTo(BigDecimal.ZERO) < 0) {
			throw new SaldoInsuficienteException("SALDO_INSUFICIENTE");
		}
	}

}
