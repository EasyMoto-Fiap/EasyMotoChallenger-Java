package br.com.easymoto.dto;

import br.com.easymoto.enums.TypeCargo;
import jakarta.validation.constraints.*;

public record FuncionarioRequest(
        @NotBlank
        @Size(max = 100)
        String nomeFunc,

        @NotBlank
        @Pattern(regexp = "^\\d{11}$", message = "O CPF deve conter apenas 11 dígitos numéricos.")
        String cpfFunc,

        @NotNull
        TypeCargo cargo,

        @NotBlank
        @Size(max = 15)
        String telefoneFunc,

        @NotBlank
        @Email
        @Size(max = 100)
        String emailFunc,

        String password,

        @NotNull
        Long filialId
) {}
