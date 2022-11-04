package com.vrtest.miniautorizador.command;

import java.math.BigDecimal;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class TransacaoCommand {

	@NotBlank
	private String numeroCartao;

	@NotBlank
	private String senhaCartao;

	@NotNull
	private BigDecimal valor;

	public String getNumeroCartao() {
		return numeroCartao;
	}

	public void setNumeroCartao(String numeroCartao) {
		this.numeroCartao = numeroCartao;
	}

	public String getSenhaCartao() {
		return senhaCartao;
	}

	public void setSenhaCartao(String senhaCartao) {
		this.senhaCartao = senhaCartao;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

}
