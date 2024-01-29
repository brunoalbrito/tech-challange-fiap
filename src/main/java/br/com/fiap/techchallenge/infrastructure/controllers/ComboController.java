package br.com.fiap.techchallenge.infrastructure.controllers;

import br.com.fiap.techchallenge.application.usecases.combo.CriaComboInteractor;
import br.com.fiap.techchallenge.application.usecases.combo.ListaComboInteractor;
import br.com.fiap.techchallenge.domain.Combo;
import br.com.fiap.techchallenge.infrastructure.controllers.mappers.ComboDtoMapper;
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

    private ComboDtoMapper comboDtoMapper;

    @PostMapping
    public ResponseEntity<ComboResponse> criaCombo(@RequestBody final ComboRequest comboRequest) {
        Combo combo = criaCombo.execute(comboRequest);

        ComboResponse response = ComboResponse.fromDomain(combo);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ComboResponse>> listaCombos() {
        List<Combo> combos = listaCombos.execute();
        List<ComboResponse> comboResponses = comboDtoMapper.toResponse(combos);

        return new ResponseEntity<>(comboResponses, HttpStatus.OK);
    }
}

