package com.redekrill.entregalogistica.service;

import com.redekrill.entregalogistica.Model.*;
import com.redekrill.entregalogistica.dto.AgendamentoDTO;
import com.redekrill.entregalogistica.dto.AgendamentoResponseDTO;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class AgendamentoMapper {

    public AgendamentoResponseDTO toDTO(Agendamento agendamento) {
        return new AgendamentoResponseDTO(
                agendamento.getId(),
                agendamento.getData(),
                agendamento.getFaixaHorario().getDescricao(),
                agendamento.getPedido().getQtdPaletes(),
                agendamento.getFornecedor(),
                agendamento.getEmailFornecedor(),
                agendamento.getTipoCaminhao().getTipo(),
                agendamento.getTipoPaletizacao().getTipo(),
                agendamento.getObs()
        );
    }

    public Agendamento toEntity(AgendamentoDTO dto) {
        Agendamento agendamento = new Agendamento();
        agendamento.setData(dto.data());

        var faixaHorario = new FaixaHorario();
        faixaHorario.setId(dto.IdfaixaHorario());
        agendamento.setFaixaHorario(faixaHorario);

        var pedido = new Pedido();
        pedido.setId(dto.idPedido());
        agendamento.setPedido(pedido);

        agendamento.setFornecedor(dto.fornecedor());
        agendamento.setEmailFornecedor(dto.emailFornecedor());

        var tipoCaminhao = new TipoCaminhao();
        tipoCaminhao.setId(dto.idTipoCaminhao());
        agendamento.setTipoCaminhao(tipoCaminhao);

        var tipoPaletizacao = new TipoPaletizacao();
        tipoPaletizacao.setId(dto.idTipoPaletizacao());
        agendamento.setTipoPaletizacao(tipoPaletizacao);

        agendamento.setObs(dto.obs());

        return agendamento;
    }
}
