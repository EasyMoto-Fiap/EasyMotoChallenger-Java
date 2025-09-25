package br.com.easymoto.dto;

import br.com.easymoto.enums.StatusLocacao;

import java.time.LocalDate;

public record ClienteLocacaoResponse(
        Long id,
        LocalDate dataInicio,
        LocalDate dataFim,
        StatusLocacao statusLocacao,
        Long clienteId,
        String clienteNome
) {}
