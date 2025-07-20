package com.redekrill.entregalogistica.dto;

import java.time.LocalDate;

public record AgendamentoResponseDTO (
        Integer id,
        LocalDate data,
        String faixaHorario,
        int qtdPaletes,
        String fornecedor,
        String emailFornecedor,
        String tipoCaminhao,
        String tipoPaletizacao,
        String obs
) {
}
