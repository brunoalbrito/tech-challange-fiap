package br.com.fiap.techchallenge.application.usecases.ingrediente;

import br.com.fiap.techchallenge.application.gateways.IngredienteGateway;
import br.com.fiap.techchallenge.domain.Ingrediente;

import java.util.List;
import java.util.UUID;

public class BuscaIngredientesPorIdInteractor {

    private final IngredienteGateway ingredienteGateway;

    public BuscaIngredientesPorIdInteractor(IngredienteGateway ingredienteGateway) {
        this.ingredienteGateway = ingredienteGateway;
    }

    public List<Ingrediente> execute(List<UUID> domainObject) {
        List<Ingrediente> ingredientes = ingredienteGateway.buscaIngredientesPorId(domainObject);

        if (ingredientes.isEmpty()) {
            throw new IllegalArgumentException("Ingredientes informados não existem.");
        }

        return ingredientes;
    }
}
