package br.com.fiap.techchallenge.domain.enums;

public enum Tipo {
    LANCHE("lanche"),
    BEBIDA("bebida"),
    SOBREMESA("sobremesa");

    public final String tipo;

    private Tipo(String tipo) {
        this.tipo = tipo;
    }

    public String getValue() {
        return tipo;
    }

    public static Tipo fromValue(String value) {
        for (Tipo enumValue : values()) {
            if (enumValue.tipo.equals(value)) {
                return enumValue;
            }
        }
        throw new IllegalArgumentException("Invalid enum value: " + value);
    }
}
