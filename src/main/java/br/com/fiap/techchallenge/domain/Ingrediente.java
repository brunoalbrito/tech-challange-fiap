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

    public static Ingrediente criaIngrediente(String descricao) throws IllegalArgumentException {
        validaDescricao(descricao);
        return new Ingrediente(UUID.randomUUID(), descricao);
    }

    private static void validaDescricao(String descricao) throws IllegalArgumentException {
        if (descricao == null || descricao.isEmpty()) {
            throw new IllegalArgumentException("Descricao deve estar preenchida");
        }
    }
}
