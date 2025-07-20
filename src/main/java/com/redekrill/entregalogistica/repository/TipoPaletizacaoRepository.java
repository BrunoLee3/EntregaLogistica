package com.redekrill.entregalogistica.repository;

import com.redekrill.entregalogistica.Model.TipoPaletizacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoPaletizacaoRepository extends JpaRepository<TipoPaletizacao, Integer> {

}
