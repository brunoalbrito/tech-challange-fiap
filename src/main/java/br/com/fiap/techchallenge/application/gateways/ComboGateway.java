package br.com.fiap.techchallenge.application.gateways;

import br.com.fiap.techchallenge.infrastructure.controllers.request.ComboRequest;
import br.com.fiap.techchallenge.infrastructure.controllers.response.ComboResponse;

import java.util.List;

public interface ComboGateway {
    ComboResponse criaCombo(ComboRequest comboRequest);
    List<ComboResponse> listaCombos();
}
