package br.com.fiap.techchallenge.infrastructure.repository;

import br.com.fiap.techchallenge.infrastructure.entity.IngredienteEntity;

public interface IngredienteRepository {
    IngredienteEntity save(IngredienteEntity ingredienteEntity);
}
