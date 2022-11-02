package com.vrtest.miniautorizador.domain;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.vrtest.miniautorizador.command.CartaoCommand;

@Entity
public class Cartao {

	@Id
	private String numeroCartao;

	private String senha;

	public String getNumeroCartao() {
		return numeroCartao;
	}

	public Cartao() {}

	public Cartao(CartaoCommand command) {
		this.numeroCartao = command.getNumeroCartao();
		this.senha = command.getSenha();
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

	@Override
	public int hashCode() {
		return Objects.hash(numeroCartao, senha);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cartao other = (Cartao) obj;
		return Objects.equals(numeroCartao, other.numeroCartao) && Objects.equals(senha, other.senha);
	}

}
