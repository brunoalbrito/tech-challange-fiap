package br.com.fiap.techchallenge.infrastructure.entity.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum Tipo {
    LANCHE("lanche"),
    BEBIDA("bebida"),
    ACOMPANHAMENTO("acompanhamento");
    private final String nome;

    public static Tipo fromValue(String value) {
        for (Tipo tipo : Tipo.values()) {
            if (tipo.nome.equals(value)) {
                return tipo;
            }
        }
        throw new IllegalArgumentException("Tipo não encontrado.");
    }

    public String getValue() {
        return nome;
    }
}
