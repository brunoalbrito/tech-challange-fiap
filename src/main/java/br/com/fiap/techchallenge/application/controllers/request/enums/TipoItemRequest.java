package br.com.fiap.techchallenge.application.controllers.request.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum TipoItemRequest {

    @JsonProperty("lanche")
    LANCHE("lanche"),

    @JsonProperty("bebida")
    BEBIDA("bebida"),

    @JsonProperty("acompanhamento")
    ACOMPANHAMENTO("acompanhamento");

    public final String tipo;

    @JsonValue
    public String getValue() {
        return tipo;
    }

    @JsonCreator
    public static TipoItemRequest fromValue(String value) {
        for (TipoItemRequest enumValue : values()) {
            if (enumValue.tipo.equals(value)) {
                return enumValue;
            }
        }
        throw new IllegalArgumentException("Invalid enum value: " + value);
    }
}
