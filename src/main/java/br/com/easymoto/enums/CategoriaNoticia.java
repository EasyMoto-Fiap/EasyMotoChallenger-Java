package br.com.easymoto.enums;

public enum CategoriaNoticia {
    TECNOLOGIA("Tecnologia"),
    SEGURANCA("Segurança"),
    MERCADO("Mercado"),
    MANUTENCAO("Manutenção"),
    GERAL("Geral");

    private final String displayName;

    CategoriaNoticia(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}