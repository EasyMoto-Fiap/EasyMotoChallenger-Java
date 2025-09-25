package br.com.easymoto.dto;

import br.com.easymoto.enums.StatusVaga;

public record VagaResponse(
        Long id,
        StatusVaga statusVaga,
        Long patioId,
        String patioNome,
        Long motoId,
        String motoPlaca,
        String fileira,
        String coluna
) {}
