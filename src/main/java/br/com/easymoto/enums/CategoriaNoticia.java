package br.com.easymoto.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum CategoriaNoticia {
    TECNOLOGIA("Tecnologia", 1),
    SEGURANCA("Segurança", 2),
    MERCADO("Mercado", 3),
    MANUTENCAO("Manutenção", 4),
    GERAL("Geral", 5);

    private final String displayName;
    private final int code;

    CategoriaNoticia(String displayName , int code) {
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