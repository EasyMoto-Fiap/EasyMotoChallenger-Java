package br.com.easymoto.enums;

public enum StatusVaga {
    OCUPADA("Ocupada"),
    LIVRE("Livre");

    private final String displayName;

    StatusVaga(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}