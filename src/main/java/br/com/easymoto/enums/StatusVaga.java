package br.com.easymoto.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum StatusVaga {
    OCUPADA("Ocupada", 1),
    INDEFINIDA("Indefinida", 2),
    LIVRE("Livre", 3);

    private final String displayName;
    private final int code;


    StatusVaga(String displayName , int code) {
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