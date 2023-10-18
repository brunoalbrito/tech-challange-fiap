package br.com.fiap.techchallenge.application.controllers.request;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class IngredienteRequest {

    private UUID id;
    private String descricao;

    public IngredienteRequest(String descricao) {
        this.descricao = descricao;
    }

    public static IngredienteRequest criaIngredienteRequest(String descricao) {
        return new IngredienteRequest(descricao);
    }
}
