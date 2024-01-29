package br.com.fiap.techchallenge.infrastructure.controllers;

import br.com.fiap.techchallenge.application.usecases.ingrediente.CriaIngredienteInteractor;
import br.com.fiap.techchallenge.domain.Ingrediente;
import br.com.fiap.techchallenge.infrastructure.controllers.request.IngredienteRequest;
import br.com.fiap.techchallenge.infrastructure.controllers.response.IngredienteResponse;
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

    private final CriaIngredienteInteractor criaIngredienteInteractor;

    @PostMapping
    public ResponseEntity<IngredienteResponse> criaIngrediente(@RequestBody IngredienteRequest ingredienteRequest) {
        Ingrediente ingrediente = criaIngredienteInteractor.execute(ingredienteRequest);

        IngredienteResponse ingredienteResponse = IngredienteResponse.fromDomain(ingrediente);

        return new ResponseEntity<>(ingredienteResponse, HttpStatus.CREATED);
    }
}
