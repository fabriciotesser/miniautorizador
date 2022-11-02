package com.vrtest.miniautorizador.controller;

import static org.hamcrest.CoreMatchers.containsString;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vrtest.miniautorizador.command.CartaoCommand;
import com.vrtest.miniautorizador.domain.Cartao;
import com.vrtest.miniautorizador.repository.CartaoRepository;


@SpringBootTest
@AutoConfigureMockMvc
public class CartaoControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
    private ObjectMapper objectMapper;

	@MockBean
	private CartaoRepository repository;

	@Test
	public void deveRetornarCartaoCadastradoComSucesso() throws Exception {
		CartaoCommand command = new CartaoCommand();
		command.setNumeroCartao("6549873025634501");
		command.setSenha("1234");
		when(repository.findByNumeroCartaoAndSenha(anyString(), anyString())).thenReturn(Optional.ofNullable(null));
		this.mockMvc.perform(MockMvcRequestBuilders
				.post("/cartoes")
				.content(objectMapper.writeValueAsBytes(command))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isCreated())
		.andExpect(content().string(containsString("numeroCartao")));
	}

	@Test
	public void deveRetornarErroQuandoCartaoExistir() throws Exception {
		CartaoCommand command = new CartaoCommand();
		command.setNumeroCartao("6549873025634501");
		command.setSenha("1234");
		when(repository.findByNumeroCartaoAndSenha(anyString(), anyString())).thenReturn(Optional.of(new Cartao(command)));
		this.mockMvc.perform(MockMvcRequestBuilders
				.post("/cartoes")
				.content(objectMapper.writeValueAsBytes(command))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isUnprocessableEntity());
	}
}