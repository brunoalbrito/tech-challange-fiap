package br.com.fiap.techchallenge.infrastructure.controllers;

import br.com.fiap.techchallenge.application.usecases.ComboInteractor;
import br.com.fiap.techchallenge.infrastructure.controllers.request.ComboRequest;
import br.com.fiap.techchallenge.infrastructure.controllers.response.ComboResponse;
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

@RestController
@AllArgsConstructor
@RequestMapping("api/combos")
public class ComboController {

    private ComboInteractor comboInteractor;

    @PostMapping
    public ResponseEntity<ComboResponse> criaCombo(@RequestBody final ComboRequest comboRequest) {
        var response = comboInteractor.criaCombo(comboRequest);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ComboResponse>> listaCombos() {
        List<ComboResponse> combos = comboInteractor.listaCombos();
        return new ResponseEntity<>(combos, HttpStatus.OK);
    }
}

