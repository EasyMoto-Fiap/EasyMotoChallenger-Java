package br.com.easymoto.dto;

import br.com.easymoto.enums.CategoriaNoticia;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record NoticiaRequest(
        @NotBlank @Size(max = 255)
        String titulo,
        @NotBlank
        String conteudo,
        @NotBlank @Size(max = 100)
        String autor,
        @NotNull
        CategoriaNoticia categoria
) {}