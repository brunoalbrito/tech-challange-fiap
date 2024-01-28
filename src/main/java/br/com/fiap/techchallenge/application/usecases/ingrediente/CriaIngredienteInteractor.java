package br.com.fiap.techchallenge.application.usecases.ingrediente;

import br.com.fiap.techchallenge.application.gateways.IngredienteGateway;
import br.com.fiap.techchallenge.domain.Ingrediente;
import br.com.fiap.techchallenge.infrastructure.controllers.request.IngredienteRequest;

import java.util.UUID;

public class CriaIngredienteInteractor {

    private final IngredienteGateway ingredienteGateway;

    public CriaIngredienteInteractor(final IngredienteGateway ingredienteGateway) {
        this.ingredienteGateway = ingredienteGateway;
    }

    public Ingrediente execute(final IngredienteRequest ingredienteRequest) {
        Ingrediente ingrediente = Ingrediente.criaIngrediente(UUID.randomUUID(), ingredienteRequest.getDescricao());

        return ingredienteGateway.salva(ingrediente);
    }
}
