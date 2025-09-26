package br.com.easymoto.dto;

import java.time.LocalDate;

public record NoticiaResponse(
        Long id,
        String titulo,
        String conteudo,
        LocalDate dataPublicacao,
        String autor,
        String categoria
) {}