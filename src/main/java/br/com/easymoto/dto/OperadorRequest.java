package br.com.easymoto.dto;

import jakarta.validation.constraints.*;

public record OperadorRequest(
        @NotBlank
        @Size(max = 100)
        String nomeOpr,

        @NotBlank
        @Pattern(regexp = "^\\d{11}$", message = "O CPF deve conter apenas 11 dígitos numéricos.")
        String cpfOpr,

        @NotBlank
        @Size(max = 14)
        String telefoneOpr,

        @NotBlank
        @Email
        @Size(max = 100)
        String emailOpr,

        @NotNull
        Long filialId
) {}
