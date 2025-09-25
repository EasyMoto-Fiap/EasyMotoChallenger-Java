package br.com.easymoto.dto;

import br.com.easymoto.enums.StatusLocacao;
import jakarta.validation.constraints.*;
import java.time.LocalDate;

public record ClienteLocacaoRequest(
        @NotNull
        LocalDate dataInicio,

        @NotNull
        LocalDate dataFim,

        @NotNull
        StatusLocacao statusLocacao,

        @NotNull
        Long clienteId
) {}
