package com.vrtest.miniautorizador.controller;

import static java.math.BigDecimal.valueOf;
import static org.hamcrest.CoreMatchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vrtest.miniautorizador.command.TransacaoCommand;
import com.vrtest.miniautorizador.domain.Cartao;
import com.vrtest.miniautorizador.domain.Transacao;
import com.vrtest.miniautorizador.exception.CartaoNaoEncontradoException;
import com.vrtest.miniautorizador.repository.TransacaoRepository;
import com.vrtest.miniautorizador.service.CartaoService;


@SpringBootTest
@AutoConfigureMockMvc
public class TransacaoControllerTest {

	private static final String OK = "OK";
	private static final BigDecimal DEZ = valueOf(10);
	private static final BigDecimal QUINHENTOS = valueOf(500);
	private static final String SENHA_CARTAO = "1234";
	private static final String NUMERO_CARTAO = "6549873025634501";
	private static final String CARTAO_INEXISTENTE = "CARTAO_INEXISTENTE";
	private static final String SALDO_INSUFICIENTE = "SALDO_INSUFICIENTE";

	@Autowired
	private MockMvc mockMvc;

	@Autowired
    private ObjectMapper objectMapper;

	@MockBean
	private TransacaoRepository repository;

	@MockBean
	private CartaoService cartaoService;

	@MockBean
	private Cartao cartao;

	@MockBean
	private Transacao transacao;

	@Test
	public void deveCriarTransacao() throws Exception {
		TransacaoCommand command = new TransacaoCommand();
		command.setNumeroCartao(NUMERO_CARTAO);
		command.setSenhaCartao(SENHA_CARTAO);
		command.setValor(DEZ);
		when(cartaoService.one(anyString(), anyString())).thenReturn(cartao);
		when(cartaoService.getSaldoCartao(NUMERO_CARTAO)).thenReturn(QUINHENTOS);
		when(repository.save(any(Transacao.class))).thenReturn(transacao);
		this.mockMvc.perform(MockMvcRequestBuilders
				.post("/transacoes")
				.content(objectMapper.writeValueAsBytes(command))
				.contentType(APPLICATION_JSON)
				.accept(APPLICATION_JSON))
		.andExpect(status().isCreated())
		.andExpect(content().string(containsString(OK)));
	}

	@Test
	public void deveRetornarErroQuandoCartaoInexistente() throws Exception {
		TransacaoCommand command = new TransacaoCommand();
		command.setNumeroCartao(NUMERO_CARTAO);
		command.setSenhaCartao(SENHA_CARTAO);
		command.setValor(QUINHENTOS);
		when(cartaoService.one(anyString(), anyString())).thenReturn(cartao);
		when(cartaoService.getSaldoCartao(NUMERO_CARTAO)).thenReturn(DEZ);
		this.mockMvc.perform(MockMvcRequestBuilders
				.post("/transacoes")
				.content(objectMapper.writeValueAsBytes(command))
				.contentType(APPLICATION_JSON)
				.accept(APPLICATION_JSON))
		.andExpect(status().isUnprocessableEntity())
		.andExpect(content().string(containsString(SALDO_INSUFICIENTE)));
	}

	@Test
	public void deveRetornarErroQuandoSaldoForInsuficiente() throws Exception {
		TransacaoCommand command = new TransacaoCommand();
		command.setNumeroCartao(NUMERO_CARTAO);
		command.setSenhaCartao(SENHA_CARTAO);
		command.setValor(QUINHENTOS);
		when(cartaoService.one(anyString(), anyString())).thenThrow(new CartaoNaoEncontradoException(CARTAO_INEXISTENTE));
		this.mockMvc.perform(MockMvcRequestBuilders
				.post("/transacoes")
				.content(objectMapper.writeValueAsBytes(command))
				.contentType(APPLICATION_JSON)
				.accept(APPLICATION_JSON))
		.andExpect(status().isUnprocessableEntity())
		.andExpect(content().string(containsString(CARTAO_INEXISTENTE)));
	}
}
