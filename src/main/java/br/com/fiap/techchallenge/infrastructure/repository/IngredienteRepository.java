package br.com.fiap.techchallenge.infrastructure.repository;

import br.com.fiap.techchallenge.infrastructure.entity.IngredienteEntity;

import java.util.List;

public interface IngredienteRepository {
    IngredienteEntity save(IngredienteEntity ingredienteEntity);

    List<IngredienteEntity> findAll();
}
