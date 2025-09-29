package br.com.easymoto.dto;

import br.com.easymoto.enums.StatusLocalizacao;
import jakarta.validation.constraints.*;
import java.time.LocalDateTime;

public record LocalizacaoRequest(
        @NotNull
        StatusLocalizacao statusLoc,

        @NotNull
        LocalDateTime dataHora,

        @NotBlank
        @Size(max = 50)
        String zonaVirtual,

        @NotNull
        Double latitude,

        @NotNull
        Double longitude
) {}