package com.redekrill.entregalogistica.dto;

import com.redekrill.entregalogistica.Model.CargosUsuario;

public record CadastroDTO(
        String email,
        String senha,
        CargosUsuario cargo
) {
}
