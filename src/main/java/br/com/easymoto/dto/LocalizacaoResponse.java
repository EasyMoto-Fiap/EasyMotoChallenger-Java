package br.com.easymoto.dto;

import br.com.easymoto.enums.StatusLocalizacao;

import java.time.LocalDateTime;

public record LocalizacaoResponse(
        Long id,
        StatusLocalizacao statusLoc,
        LocalDateTime dataHora,
        String zonaVirtual,
        Double latitude,
        Double longitude
) {}
