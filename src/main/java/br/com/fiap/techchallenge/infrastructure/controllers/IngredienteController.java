package br.com.fiap.techchallenge.infrastructure.controllers;

import br.com.fiap.techchallenge.application.usecases.IngredienteInteractor;
import br.com.fiap.techchallenge.infrastructure.controllers.request.IngredienteRequest;
import br.com.fiap.techchallenge.infrastructure.controllers.response.IngredienteResponse;
import br.com.fiap.techchallenge.infrastructure.persistence.entity.IngredienteEntity;
import br.com.fiap.techchallenge.infrastructure.persistence.mappers.IngredienteEntityMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("api/ingredientes")
public class IngredienteController {

    private final IngredienteInteractor ingredienteInteractor;

    private final IngredienteEntityMapper ingredienteEntityMapper;

    @PostMapping
    public ResponseEntity<IngredienteResponse> criaIngrediente(@RequestBody IngredienteRequest ingredienteRequest) {
        IngredienteEntity ingrediente = ingredienteInteractor.criaIngrediente(ingredienteRequest);
        IngredienteResponse response = ingredienteEntityMapper.toResponse(ingrediente);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
