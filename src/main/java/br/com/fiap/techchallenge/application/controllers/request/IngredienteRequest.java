package br.com.fiap.techchallenge.application.controllers.request;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class IngredienteRequest {
    private String descricao;

    public static IngredienteRequest criaIngredienteRequest(String descricao) {
        return new IngredienteRequest(descricao);
    }
}
