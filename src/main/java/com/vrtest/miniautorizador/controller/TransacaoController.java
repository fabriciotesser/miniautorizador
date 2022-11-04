package com.vrtest.miniautorizador.controller;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.UNPROCESSABLE_ENTITY;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vrtest.miniautorizador.command.TransacaoCommand;
import com.vrtest.miniautorizador.exception.CartaoNaoEncontradoException;
import com.vrtest.miniautorizador.exception.SaldoInsuficienteException;
import com.vrtest.miniautorizador.service.TransacaoService;

@RestController
@RequestMapping(value = "transacoes")
public class TransacaoController {

	private final TransacaoService service;

	@Autowired
	public TransacaoController(TransacaoService service) {
		this.service = service;
	}

	@PostMapping
	public ResponseEntity<String> criarCartao(@Valid @RequestBody TransacaoCommand command) {
		try {
			this.service.gerarTransacao(command);
			return new ResponseEntity<String>(CREATED);
		} catch (SaldoInsuficienteException | CartaoNaoEncontradoException e) {
			return new ResponseEntity<String>(e.getMessage(), UNPROCESSABLE_ENTITY);
		}
	}
}
