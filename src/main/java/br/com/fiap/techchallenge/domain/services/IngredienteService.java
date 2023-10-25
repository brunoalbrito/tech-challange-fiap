package br.com.fiap.techchallenge.domain.services;

import br.com.fiap.techchallenge.application.controllers.request.IngredienteRequest;
import br.com.fiap.techchallenge.domain.Ingrediente;
import br.com.fiap.techchallenge.infrastructure.entity.IngredienteEntity;
import br.com.fiap.techchallenge.infrastructure.repository.IngredienteRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class IngredienteService {

    private IngredienteRepository ingredienteRepository;

    public Ingrediente criaIngrediente(IngredienteRequest ingredienteRequest) {
        Ingrediente ingrediente = Ingrediente.criaIngrediente(UUID.randomUUID(), ingredienteRequest.getDescricao());

        ingredienteRepository.save(IngredienteEntity.criaEntity(ingrediente));

        return ingrediente;
    }
}
