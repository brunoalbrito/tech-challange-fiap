package br.com.fiap.techchallenge.infrastructure.controllers;

import br.com.fiap.techchallenge.application.usecases.combo.CriaComboInteractor;
import br.com.fiap.techchallenge.application.usecases.combo.ListaComboInteractor;
import br.com.fiap.techchallenge.infrastructure.controllers.request.ComboRequest;
import br.com.fiap.techchallenge.infrastructure.controllers.response.ComboResponse;
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

    private CriaComboInteractor criaCombo;

    private ListaComboInteractor listaCombos;

    @PostMapping
    public ResponseEntity<ComboResponse> criaCombo(@RequestBody final ComboRequest comboRequest) {
        ComboResponse response = criaCombo.execute(comboRequest);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ComboResponse>> listaCombos() {
        List<ComboResponse> combos = listaCombos.execute();
        return new ResponseEntity<>(combos, HttpStatus.OK);
    }
}

