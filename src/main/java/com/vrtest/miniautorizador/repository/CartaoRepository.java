package com.vrtest.miniautorizador.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vrtest.miniautorizador.domain.Cartao;

public interface CartaoRepository extends JpaRepository<Cartao, String> {

	Optional<Cartao> findByNumeroCartaoAndSenha(String numeroCartao, String senha);
}
