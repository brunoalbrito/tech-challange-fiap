package br.com.fiap.techchallenge.application.usecases;

import br.com.fiap.techchallenge.application.gateways.IngredienteGateway;
import br.com.fiap.techchallenge.infrastructure.controllers.request.IngredienteRequest;
import br.com.fiap.techchallenge.infrastructure.persistence.entity.IngredienteEntity;

public class IngredienteInteractor {

    private final IngredienteGateway ingredienteGateway;

    public IngredienteInteractor(IngredienteGateway ingredienteGateway) {
        this.ingredienteGateway = ingredienteGateway;
    }

    public IngredienteEntity criaIngrediente(IngredienteRequest ingredienteRequest) {
        return ingredienteGateway.crianIngrediente(ingredienteRequest);
    }
}
