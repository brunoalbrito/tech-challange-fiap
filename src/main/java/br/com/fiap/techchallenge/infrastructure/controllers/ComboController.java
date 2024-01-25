package br.com.fiap.techchallenge.infrastructure.controllers;

import br.com.fiap.techchallenge.application.usecases.ComboUserInteractor;
import br.com.fiap.techchallenge.infrastructure.controllers.request.ComboRequest;
import br.com.fiap.techchallenge.infrastructure.controllers.response.ComboResponse;
import br.com.fiap.techchallenge.infrastructure.controllers.response.IngredienteResponse;
import br.com.fiap.techchallenge.infrastructure.controllers.response.ProdutoResponse;
import br.com.fiap.techchallenge.domain.Combo;
import br.com.fiap.techchallenge.domain.services.ComboService;
import br.com.fiap.techchallenge.infrastructure.persistence.mappers.ComboEntityMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@RequestMapping("api/combos")
public class ComboController {

    private ComboService comboService;

    private ComboUserInteractor comboUserInteractor;

    private ComboEntityMapper comboEntityMapper;

    @PostMapping
    public ResponseEntity<ComboResponse> criaCombo(@RequestBody final ComboRequest comboRequest) {
        Combo combo = comboUserInteractor.criaCombo(comboRequest);
        var response = comboEntityMapper.toResponse(combo);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ComboResponse>> listaCombos() {
        List<Combo> combos = comboUserInteractor.listaCombos();
        return new ResponseEntity<>(comboEntityMapper.toResponse(combos), HttpStatus.OK);
    }
}

