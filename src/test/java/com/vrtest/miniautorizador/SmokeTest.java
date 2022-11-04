package com.vrtest.miniautorizador;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.vrtest.miniautorizador.controller.CartaoController;
import com.vrtest.miniautorizador.controller.TransacaoController;

@SpringBootTest
public class SmokeTest {

	@Autowired
	private CartaoController cartaoController;

	@Autowired
	private TransacaoController transacaoController;

	@Test
	public void contextLoads() throws Exception {
		assertThat(cartaoController).isNotNull();
		assertThat(transacaoController).isNotNull();
	}
}
