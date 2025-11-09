package br.com.easymoto.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum StatusMoto {

    DISPONIVEL("Disponível", 0),
    EM_USO("Em uso", 1),
    MANUTENCAO("Manutenção", 2);

    private final String descricao;

    @JsonValue
    private final int code;

    StatusMoto(String descricao, int code) {
        this.descricao = descricao;
        this.code = code;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getDisplayName() {
        return descricao;
    }

    public int getCode() {
        return code;
    }

    public static StatusMoto fromCode(Integer code) {
        if (code == null) {
            return null;
        }
        for (StatusMoto status : StatusMoto.values()) {
            if (status.getCode() == code) {
                return status;
            }
        }
        throw new IllegalArgumentException("Código inválido para StatusMoto: " + code);
    }
}
