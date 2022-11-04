package com.vrtest.miniautorizador.repository;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.vrtest.miniautorizador.domain.Cartao;

public interface CartaoRepository extends JpaRepository<Cartao, String> {

	Optional<Cartao> findByNumeroCartaoAndSenha(String numeroCartao, String senha);

	@Query(value="SELECT SUM(t.valor) FROM Transacao t WHERE t.cartao.numeroCartao = :numeroCartao")
	BigDecimal getSaldoCartao(@Param("numeroCartao") String numeroCartao);
}
