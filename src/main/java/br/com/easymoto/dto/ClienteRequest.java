package br.com.easymoto.dto;

import jakarta.validation.constraints.*;

public record ClienteRequest(
        @NotBlank
        @Size(max = 100)
        String nomeCliente,

        @NotBlank
        @Pattern(regexp = "^\\d{11}$", message = "O CPF deve conter apenas 11 dígitos numéricos.")
        String cpfCliente,

        @NotBlank
        @Size(max = 15)
        String telefoneCliente,

        @NotBlank
        @Email
        @Size(max = 100)
        String emailCliente
) {}
