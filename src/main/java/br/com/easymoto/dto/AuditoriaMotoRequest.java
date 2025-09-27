package br.com.easymoto.dto;

import jakarta.validation.constraints.NotBlank;

public record AuditoriaMotoRequest(

        Long id,

        @NotBlank
        String userName,

        @NotBlank
        String operacao,

        String oldValues,
        String newValues
) {}