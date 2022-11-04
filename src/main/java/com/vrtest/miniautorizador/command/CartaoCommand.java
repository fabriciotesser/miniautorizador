package com.vrtest.miniautorizador.command;

import javax.validation.constraints.NotBlank;

import com.vrtest.miniautorizador.domain.Cartao;

public class CartaoCommand {

	@NotBlank
	private String numeroCartao;

	@NotBlank
	private String senha;
	
	public CartaoCommand() {}
	
	public CartaoCommand(Cartao cartao) {
		this.numeroCartao = cartao.getNumeroCartao();
		this.senha = cartao.getSenha();
	}

	public String getNumeroCartao() {
		return numeroCartao;
	}

	public void setNumeroCartao(String numeroCartao) {
		this.numeroCartao = numeroCartao;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

}
