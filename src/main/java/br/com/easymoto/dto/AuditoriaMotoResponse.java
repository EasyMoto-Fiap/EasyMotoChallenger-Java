package br.com.easymoto.dto;

import java.time.LocalDateTime;

public record AuditoriaMotoResponse(
        Long id,
        String userName,
        String operacao,
        LocalDateTime dataHora,
        String oldValues,
        String newValues
) {}