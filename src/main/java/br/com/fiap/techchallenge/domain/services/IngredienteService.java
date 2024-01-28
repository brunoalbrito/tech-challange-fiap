package br.com.fiap.techchallenge.domain.services;

import br.com.fiap.techchallenge.infrastructure.controllers.request.IngredienteRequest;
import br.com.fiap.techchallenge.domain.Ingrediente;
import br.com.fiap.techchallenge.infrastructure.persistence.entity.IngredienteEntity;
import br.com.fiap.techchallenge.infrastructure.persistence.repository.IngredienteRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Deprecated
@AllArgsConstructor
public class IngredienteService {

    private IngredienteRepository ingredienteRepository;

    public Ingrediente criaIngrediente(IngredienteRequest ingredienteRequest) {
        Ingrediente ingrediente = Ingrediente.criaIngrediente(UUID.randomUUID(), ingredienteRequest.getDescricao());

        ingredienteRepository.save(IngredienteEntity.criaEntity(ingrediente));

        return ingrediente;
    }

    public List<Ingrediente> buscaIngredientesPorId(List<UUID> ingredientesId) {
        List<IngredienteEntity> ingredientesEntity = ingredientesId.stream().
                map(ingredienteId -> ingredienteRepository.findById(ingredienteId)
                        .orElseThrow(() -> new IllegalArgumentException("Ingrediente não encontrado."))).
                toList();

        return ingredientesEntity.stream().map(IngredienteEntity::toDomain).collect(Collectors.toList());
    }
}
