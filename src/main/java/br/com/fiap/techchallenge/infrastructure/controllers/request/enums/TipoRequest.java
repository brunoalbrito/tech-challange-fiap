package br.com.fiap.techchallenge.infrastructure.controllers.request.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum TipoRequest {

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
    public static TipoRequest fromValue(String value) {
        for (TipoRequest enumValue : values()) {
            if (enumValue.tipo.equals(value)) {
                return enumValue;
            }
        }
        throw new IllegalArgumentException("Invalid enum value: " + value);
    }
}
