package br.com.fiap.techchallenge.domain;

import br.com.fiap.techchallenge.infrastructure.entity.IngredienteEntity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
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

    public static Ingrediente criaIngrediente(UUID id, String descricao) {
        return new Ingrediente(id, descricao);
    }

    private static void validaDescricao(String descricao) throws IllegalArgumentException {
        if (descricao == null || descricao.isEmpty()) {
            throw new IllegalArgumentException("Descricao deve estar preenchida");
        }
    }

    public static List<Ingrediente> criaListaIngredientes(List<IngredienteEntity> ingredientesEntity) {
        return ingredientesEntity.stream()
                .map(ingredienteEntity -> new Ingrediente(ingredienteEntity.getId(), ingredienteEntity.getDescricao()))
                .toList();
    }
}
