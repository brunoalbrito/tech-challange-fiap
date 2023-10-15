package br.com.fiap.techchallenge.domain.services;

import br.com.fiap.techchallenge.application.controllers.request.IngredienteRequest;
import br.com.fiap.techchallenge.domain.Ingrediente;
import br.com.fiap.techchallenge.infrastructure.entity.IngredienteEntity;
import br.com.fiap.techchallenge.infrastructure.repository.IngredienteRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class IngredienteService {

    private IngredienteRepository ingredienteRepository;

    public Ingrediente criaIngrediente(IngredienteRequest ingredienteRequest) {
        Ingrediente ingrediente = Ingrediente.criaIngrediente(ingredienteRequest.getDescricao());

        ingredienteRepository.save(IngredienteEntity.criaEntity(ingrediente));

        return ingrediente;
    }

    public List<Ingrediente> listaTodosIngredientes() {
        List<IngredienteEntity> ingredientesEntity = ingredienteRepository.findAll();

        return Ingrediente.criaListaIngredientes(ingredientesEntity);

    }
}
