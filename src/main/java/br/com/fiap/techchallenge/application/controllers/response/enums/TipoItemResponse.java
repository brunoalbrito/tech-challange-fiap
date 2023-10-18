package br.com.fiap.techchallenge.application.controllers.response.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum TipoItemResponse {
    LANCHE("lanche"),

    BEBIDA("bebida"),

    SOBREMESA("sobremesa");

    public String tipo;

    @JsonValue
    public String getValue() {
        return tipo;
    }
}
