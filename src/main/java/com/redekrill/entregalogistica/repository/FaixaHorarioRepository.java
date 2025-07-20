package com.redekrill.entregalogistica.repository;

import com.redekrill.entregalogistica.Model.FaixaHorario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FaixaHorarioRepository extends JpaRepository<FaixaHorario, Integer> {
}
