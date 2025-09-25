package br.com.easymoto.dto;

import br.com.easymoto.enums.StatusMoto;
import jakarta.validation.constraints.*;

public record MotoRequest(
        @NotBlank
        @Size(max = 10)
        String placa,

        @NotBlank
        @Size(max = 50)
        String modelo,

        @NotNull
        Integer anoFabricacao,

        @NotNull
        StatusMoto statusMoto,

        @NotNull
        Long locacaoId,

        @NotNull
        Long localizacaoId
) {}
