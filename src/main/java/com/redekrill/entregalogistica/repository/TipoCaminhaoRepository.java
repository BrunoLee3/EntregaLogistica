package com.redekrill.entregalogistica.repository;

import com.redekrill.entregalogistica.Model.TipoCaminhao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoCaminhaoRepository extends JpaRepository<TipoCaminhao, Integer> {
}
