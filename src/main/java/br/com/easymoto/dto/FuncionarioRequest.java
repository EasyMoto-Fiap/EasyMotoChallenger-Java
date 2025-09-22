package br.com.easymoto.dto;

import br.com.easymoto.enums.TypeCargo;
import jakarta.validation.constraints.*;

public record FuncionarioRequest(
        @NotBlank
        @Size(max = 100)
        String nomeFunc,

        @NotBlank
        @Size(min = 11, max = 11)
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

        @NotBlank
        String password,

        @NotNull
        Long filialId
) {}
