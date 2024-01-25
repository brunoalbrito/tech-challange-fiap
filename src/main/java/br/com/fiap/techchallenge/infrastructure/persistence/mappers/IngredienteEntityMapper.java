package br.com.fiap.techchallenge.infrastructure.persistence.mappers;

import br.com.fiap.techchallenge.infrastructure.controllers.response.IngredienteResponse;
import br.com.fiap.techchallenge.infrastructure.persistence.entity.IngredienteEntity;

public class IngredienteEntityMapper {
    public IngredienteResponse toResponse(IngredienteEntity ingrediente) {
        return IngredienteResponse.builder()
                .id(ingrediente.getId().toString())
                .descricao(ingrediente.getDescricao())
                .build();
    }
}
