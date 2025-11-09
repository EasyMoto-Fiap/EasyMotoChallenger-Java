package br.com.easymoto.dto;

import br.com.easymoto.enums.StatusVaga;
import jakarta.validation.constraints.*;

public record VagaRequest(
        @NotNull
        StatusVaga statusVaga,

        @NotNull
        Long patioId,

        Long motoId,

        @NotBlank
        @Size(max = 1)
        String fileira,

        @NotBlank
        @Size(max = 1)
        String coluna
) {}
