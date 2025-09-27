package br.com.easymoto.enums;

public enum StatusLocacao {
    ABERTA("Aberta"),
    FINALIZADA("Finalizada"),
    CANCELADA("Cancelada");

    private final String displayName;

    StatusLocacao(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}