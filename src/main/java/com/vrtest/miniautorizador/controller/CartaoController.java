package com.vrtest.miniautorizador.controller;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.HttpStatus.UNPROCESSABLE_ENTITY;

import java.math.BigDecimal;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vrtest.miniautorizador.command.CartaoCommand;
import com.vrtest.miniautorizador.exception.CartaoEncontradoException;
import com.vrtest.miniautorizador.exception.CartaoNaoEncontradoException;
import com.vrtest.miniautorizador.service.CartaoService;

@RestController
@RequestMapping(value = "cartoes")
public class CartaoController {

	private final CartaoService service;

	@Autowired
	public CartaoController(CartaoService service) {
		this.service = service;
	}

	@PostMapping
	public ResponseEntity<CartaoCommand> criarCartao(@Valid @RequestBody CartaoCommand command) {
		try {
			command = this.service.cadastrarCartao(command);
			return new ResponseEntity<CartaoCommand>(command, CREATED);
		} catch (CartaoEncontradoException e) {
			return new ResponseEntity<CartaoCommand>(command, UNPROCESSABLE_ENTITY);
		}
	}

	@GetMapping("/{numeroCartao}")
	public ResponseEntity<BigDecimal> getSaldoCartao(@PathVariable String numeroCartao) {
		try {
			BigDecimal saldoCartao = this.service.getSaldoCartao(numeroCartao);
			return new ResponseEntity<BigDecimal>(saldoCartao, OK);
		} catch (CartaoNaoEncontradoException e) {
			return new ResponseEntity<BigDecimal>(NOT_FOUND);
		}
	}
}
