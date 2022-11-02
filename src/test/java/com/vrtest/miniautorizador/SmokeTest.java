package com.vrtest.miniautorizador;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.vrtest.miniautorizador.controller.CartaoController;

@SpringBootTest
public class SmokeTest {

	@Autowired
	private CartaoController controller;

	@Test
	public void contextLoads() throws Exception {
		assertThat(controller).isNotNull();
	}
}
