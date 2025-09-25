package br.com.easymoto.dto;

import br.com.easymoto.enums.StatusMoto;

public record MotoResponse(
        Long id,
        String placa,
        String modelo,
        Integer anoFabricacao,
        StatusMoto statusMoto,
        Long locacaoId,
        Long localizacaoId
) {}
