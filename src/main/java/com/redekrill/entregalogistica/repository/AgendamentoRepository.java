package com.redekrill.entregalogistica.repository;

import com.redekrill.entregalogistica.Model.Agendamento;
import com.redekrill.entregalogistica.Model.FaixaHorario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AgendamentoRepository extends JpaRepository<Agendamento, Integer> {

    List<Agendamento> findByData(LocalDate data);

    List<Agendamento> findByFaixaHorario(FaixaHorario faixaHorario);
}
