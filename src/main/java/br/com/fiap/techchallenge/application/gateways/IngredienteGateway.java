package br.com.fiap.techchallenge.application.gateways;

import br.com.fiap.techchallenge.infrastructure.controllers.request.IngredienteRequest;
import br.com.fiap.techchallenge.infrastructure.persistence.entity.IngredienteEntity;

public interface IngredienteGateway {
    IngredienteEntity crianIngrediente(IngredienteRequest ingredienteRequest);
}
