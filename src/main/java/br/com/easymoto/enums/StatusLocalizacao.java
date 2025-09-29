package br.com.easymoto.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum StatusLocalizacao {
    DISPONIVEL("Disponível", 1),
    EM_USO("Em uso", 2),
    MANUTENCAO("Manutenção", 3);

    private final String displayName;
    private final int code;



    StatusLocalizacao(String displayName , int code) {
        this.displayName = displayName;
        this.code = code;
    }

    public String getDisplayName() {
        return displayName;
    }

    @JsonValue
    public int getCode() {
        return code;
    }
}