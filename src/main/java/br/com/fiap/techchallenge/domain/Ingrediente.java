package br.com.fiap.techchallenge.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Ingrediente {

    private UUID id;
    private String descricao;

    public static Ingrediente criaIngrediente(UUID id, String descricao) throws IllegalArgumentException {
        validaDescricao(descricao);
        return new Ingrediente(id, descricao);
    }

    private static void validaDescricao(String descricao) throws IllegalArgumentException {
        if (descricao == null || descricao.isEmpty()) {
            throw new IllegalArgumentException("Descricao deve estar preenchida");
        }
    }
}
