package br.com.fiap.techchallenge.infrastructure.controllers;

import br.com.fiap.techchallenge.application.usecases.combo.CriaComboInteractor;
import br.com.fiap.techchallenge.application.usecases.combo.ListaComboInteractor;
import br.com.fiap.techchallenge.infrastructure.controllers.request.ComboRequest;
import br.com.fiap.techchallenge.infrastructure.controllers.response.ComboResponse;
import br.com.fiap.techchallenge.infrastructure.controllers.response.IngredienteResponse;
import br.com.fiap.techchallenge.infrastructure.controllers.response.ProdutoResponse;
import br.com.fiap.techchallenge.domain.Combo;
import br.com.fiap.techchallenge.domain.services.ComboService;
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

