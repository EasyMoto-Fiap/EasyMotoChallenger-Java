package br.com.easymoto.enums;

public enum StatusLocacao {
    ATIVA("Ativa"),
    FINALIZADA("Finalizada"),
    PENDENTE("Pendente");

    private final String displayName;

    StatusLocacao(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}