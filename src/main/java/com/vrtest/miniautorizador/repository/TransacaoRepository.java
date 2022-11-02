package com.vrtest.miniautorizador.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vrtest.miniautorizador.domain.Transacao;

public interface TransacaoRepository extends JpaRepository<Transacao, Long> {

}
