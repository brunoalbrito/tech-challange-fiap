package br.com.fiap.techchallenge.infrastructure.persistence.gateways;

import br.com.fiap.techchallenge.application.gateways.IngredienteGateway;
import br.com.fiap.techchallenge.domain.Ingrediente;
import br.com.fiap.techchallenge.infrastructure.persistence.entity.IngredienteEntity;
import br.com.fiap.techchallenge.infrastructure.persistence.repository.IngredienteRepository;

import java.util.List;
import java.util.UUID;

public class IngredienteRepositoryGateway implements IngredienteGateway {

    private final IngredienteRepository ingredienteRepository;

    public IngredienteRepositoryGateway(IngredienteRepository ingredienteRepository) {
        this.ingredienteRepository = ingredienteRepository;
    }

    @Override
    public List<Ingrediente> buscaIngredientesPorId(List<UUID> ids) {
        return ingredienteRepository.findAllById(ids).stream().map(IngredienteEntity::toDomain).toList();
    }
}
