package br.com.fiap.techchallenge.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Ingrediente {
    private String descricao;

    public static Ingrediente criaIngrediente(String descricao) throws IllegalArgumentException {
        validaDescricao(descricao);
        return new Ingrediente(descricao);
    }

    private static void validaDescricao(String descricao) throws IllegalArgumentException {
        if(descricao.isEmpty()){
            throw new IllegalArgumentException("Descricao deve estar preenchida");
        }
    }
}
