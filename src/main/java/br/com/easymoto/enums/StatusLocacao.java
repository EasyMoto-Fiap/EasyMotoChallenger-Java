package br.com.easymoto.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

public enum StatusLocacao {
    ABERTA("Aberta", 0),
    FINALIZADA("Finalizada", 1),
    CANCELADA("Cancelada", 2);

    @Getter
    private final String displayName;
    private final int code;

    StatusLocacao(String displayName, int code) {
        this.displayName = displayName;
        this.code = code;
    }

    @JsonValue
    public int getCode() {
        return code;
    }
}
