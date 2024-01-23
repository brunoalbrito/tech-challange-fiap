package br.com.fiap.techchallenge.infrastructure.persistence.entity.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum TipoEntity {
    LANCHE("lanche"),
    BEBIDA("bebida"),
    ACOMPANHAMENTO("acompanhamento");
    private final String valor;
    public static TipoEntity fromValue(String value) {
        for (TipoEntity tipo : TipoEntity.values()) {
            if (tipo.valor.equals(value)) {
                return tipo;
            }
        }
        throw new IllegalArgumentException("Tipo não encontrado.");
    }

    public String getValue() {
        return valor;
    }
}
