package com.redekrill.entregalogistica.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record AgendamentoDTO(
        @NotNull(message = "A data é obrigatória")
        LocalDate data,

        @NotNull(message = "A faixa horária é obrigatória")
        Integer IdfaixaHorario,

        @NotNull(message = "O ID do pedido é obrigatório")
        Integer idPedido,

        @NotBlank(message = "O nome do fornecedor é obrigatório")
        String fornecedor,

        @Email(message = "E-mail inválido")
        @NotBlank(message = "O e-mail do fornecedor é obrigatório")
        String emailFornecedor,

        @NotNull(message = "O tipo de caminhão é obrigatório")
        Integer idTipoCaminhao,

        @NotNull(message = "O tipo de paletização é obrigatório")
        Integer idTipoPaletizacao,

        String obs
) {

}
