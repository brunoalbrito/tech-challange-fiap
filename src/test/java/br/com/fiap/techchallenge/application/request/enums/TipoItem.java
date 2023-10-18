package br.com.fiap.techchallenge.application.request.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum TipoItem {
    LANCHE("lanche"),

    BEBIDA("bebida"),

    SOBREMESA("sobremesa");

    public String tipo;

    @JsonValue
    public String getValue() {
        return tipo;
    }
}