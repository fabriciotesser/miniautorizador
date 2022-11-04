package com.vrtest.miniautorizador.domain;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.LAZY;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.vrtest.miniautorizador.command.CartaoCommand;

@Entity
public class Cartao {

	@Id
	private String numeroCartao;

	private String senha;

	@OneToMany(fetch = LAZY, cascade = ALL)
	private List<Transacao> transacoes;

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

	public List<Transacao> getTransacoes() {
		return transacoes;
	}

	public void setTransacoes(List<Transacao> transacoes) {
		this.transacoes = transacoes;
	}

	public void addTransacao(Transacao transacao) {
		if(this.transacoes == null)
			this.transacoes = new ArrayList<Transacao>();
	
		if(!this.transacoes.contains(transacao)) {
			this.transacoes.add(transacao);
			transacao.setCartao(this);
		}
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
