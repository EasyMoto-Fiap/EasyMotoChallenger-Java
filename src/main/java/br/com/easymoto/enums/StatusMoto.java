package br.com.easymoto.enums;

public enum StatusMoto {
    DISPONIVEL("Disponível"),
    EM_LOCACAO("Em Locação"),
    EM_MANUTENCAO("Em Manutenção");

    private final String displayName;

    StatusMoto(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}