package br.com.fiap.techchallenge.application.controllers;

import br.com.fiap.techchallenge.application.controllers.request.IngredienteRequest;
import br.com.fiap.techchallenge.application.controllers.response.IngredienteResponse;
import br.com.fiap.techchallenge.domain.Ingrediente;
import br.com.fiap.techchallenge.domain.services.IngredienteService;
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

    private IngredienteService ingredienteService;

    @PostMapping
    public ResponseEntity<IngredienteResponse> criaIngrediente(@RequestBody IngredienteRequest ingredienteRequest) {
        Ingrediente ingrediente = ingredienteService.criaIngrediente(ingredienteRequest);

        IngredienteResponse ingredienteResponse = IngredienteResponse.builder()
                .id(ingrediente.getId().toString())
                .descricao(ingrediente.getDescricao())
                .build();

        return new ResponseEntity<>(ingredienteResponse, HttpStatus.CREATED);
    }
}
