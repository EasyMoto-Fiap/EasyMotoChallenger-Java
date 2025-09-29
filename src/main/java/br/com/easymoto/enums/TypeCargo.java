package br.com.easymoto.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum TypeCargo {
    USER(1), ADMIN(2);

    private final int code;

    TypeCargo( int code) {
        this.code = code;
    }

    @JsonValue
    public int getCode() {
        return code;
    }
}
