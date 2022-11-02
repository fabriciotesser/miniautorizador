package com.vrtest.miniautorizador.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vrtest.miniautorizador.command.CartaoCommand;
import com.vrtest.miniautorizador.exception.CartaoEncontradoException;
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
			return new ResponseEntity<CartaoCommand>(command, HttpStatus.CREATED);
		} catch (CartaoEncontradoException e) {
			return new ResponseEntity<CartaoCommand>(command, HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}
}
