package com.redekrill.entregalogistica.service;

import com.redekrill.entregalogistica.Model.Agendamento;
import com.redekrill.entregalogistica.Model.FaixaHorario;
import com.redekrill.entregalogistica.dto.AgendamentoDTO;
import com.redekrill.entregalogistica.dto.AgendamentoResponseDTO;
import com.redekrill.entregalogistica.repository.AgendamentoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

import java.time.LocalTime;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AgendamentoService {

    private int limitePaletesDia = 480;
    private int limitePaletesFaixaHoraria = 120;

    private final AgendamentoRepository repository;
    private final AgendamentoMapper mapper;

    @Autowired
    public AgendamentoService(AgendamentoRepository repository, AgendamentoMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public List<AgendamentoResponseDTO> listAgendamentos() {
        return repository.findAll()
                .stream()
                .map(mapper::toDTO)
                .sorted(Comparator
                        .comparing(AgendamentoResponseDTO::data)
                        .thenComparing(dto -> LocalTime.parse(dto.faixaHorario().substring(0, 5)))
                )
                .collect(Collectors.toList());
    }

    public Optional<AgendamentoResponseDTO> findAgentamento(Integer id){
        return repository.findById(id)
                .map(mapper::toDTO);
    }

    public AgendamentoDTO createAgendamento(AgendamentoDTO dto){

        var agendamento = mapper.toEntity(dto);

        int totalPaletesData = calculaTotalPaletesDoDia(agendamento.getData(), agendamento.getPedido().getQtdPaletes());
        int totalPaletesFaixaHoraria = calculaTotalPaletesFaixaHora(agendamento.getFaixaHorario(), agendamento.getPedido().getQtdPaletes());

        if (totalPaletesData > limitePaletesDia) {
            System.out.println("Limite de 480 paletes por dia excedido");
        }

        if (totalPaletesFaixaHoraria > limitePaletesFaixaHoraria) {
            System.out.println("Limite de 120 paletes por faixa horária");
        }

        repository.save(agendamento);
        return dto;
    }

    public Agendamento updateAgendamento(Agendamento agendamento, Integer id){
        return repository.findById(id)
                .map(agendamentoExistente -> {
                    agendamento.setId(id);
                    return repository.save(agendamento);
                })
                .orElseThrow(() -> new EntityNotFoundException("Angendamento não encontrado"));
    }

    public void deleteAgendamento(Integer id){
        repository.deleteById(id);
    }

    private int calculaTotalPaletesDoDia(LocalDate data, int paletesNovos) {
        List<Agendamento> agendamentos = repository.findByData(data);

        int paletesExistentes = agendamentos.stream()
                .mapToInt(a -> a.getPedido().getQtdPaletes())
                .sum();

        return paletesExistentes + paletesNovos;
    }

    private int calculaTotalPaletesFaixaHora(FaixaHorario faixaHorario, int paletesNovos){
        List<Agendamento> agendamentos = repository.findByFaixaHorario(faixaHorario);

        int paletesExistentes = agendamentos.stream()
                .mapToInt(a -> a.getPedido().getQtdPaletes())
                .sum();

        return paletesExistentes + paletesNovos;
    }
}
