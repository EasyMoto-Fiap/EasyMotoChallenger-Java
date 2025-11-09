package br.com.easymoto.enums;


import java.util.Arrays;

public enum StatusLocalizacao {

    DENTRO_DA_ZONA_VIRTUAL(0, "Dentro da Zona Virtual"),
    FORA_DA_ZONA_VIRTUAL(1, "Fora da Zona Virtual"),
    EM_ALERTA(2, "Em Alerta");

    private final int codigo;
    private final String descricao;

    StatusLocalizacao(int codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public static StatusLocalizacao fromCodigo(int codigo) {
        return Arrays.stream(values())
                .filter(status -> status.codigo == codigo)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Código inválido para StatusLocalizacao: " + codigo));
    }
}
