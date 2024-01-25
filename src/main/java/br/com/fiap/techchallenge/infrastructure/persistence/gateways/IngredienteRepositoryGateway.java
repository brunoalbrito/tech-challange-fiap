package br.com.fiap.techchallenge.infrastructure.persistence.gateways;

import br.com.fiap.techchallenge.application.gateways.IngredienteGateway;
import br.com.fiap.techchallenge.domain.Ingrediente;
import br.com.fiap.techchallenge.infrastructure.controllers.request.IngredienteRequest;
import br.com.fiap.techchallenge.infrastructure.persistence.entity.IngredienteEntity;
import br.com.fiap.techchallenge.infrastructure.persistence.repository.IngredienteRepository;

import java.util.UUID;

public class IngredienteRepositoryGateway implements IngredienteGateway {

    private final IngredienteRepository ingredienteRepository;

    public IngredienteRepositoryGateway(IngredienteRepository ingredienteRepository) {
        this.ingredienteRepository = ingredienteRepository;
    }

    @Override
    public IngredienteEntity crianIngrediente(IngredienteRequest ingredienteRequest) {
        Ingrediente ingrediente = Ingrediente.criaIngrediente(UUID.randomUUID(), ingredienteRequest.getDescricao());
        return ingredienteRepository.save(IngredienteEntity.criaEntity(ingrediente));
    }
}
