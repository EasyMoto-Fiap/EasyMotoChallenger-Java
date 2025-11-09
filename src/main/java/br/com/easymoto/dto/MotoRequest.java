package br.com.easymoto.dto;

import br.com.easymoto.enums.StatusMoto;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class MotoRequest {

        private Long id;

        @NotBlank
        @Size(max = 10)
        private String placa;

        @NotBlank
        @Size(max = 50)
        private String modelo;

        @NotNull
        @Min(1980)
        private Integer anoFabricacao;

        @NotNull
        private StatusMoto status;

        @NotNull
        private Long locacaoId;

        @NotNull
        private Long localizacaoId;
}
