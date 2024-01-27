package br.com.fiap.techchallenge.application.usecases.combo;

import br.com.fiap.techchallenge.application.gateways.ComboGateway;
import br.com.fiap.techchallenge.infrastructure.controllers.request.ComboRequest;
import br.com.fiap.techchallenge.infrastructure.controllers.response.ComboResponse;

public class CriaComboInteractor {

    private final ComboGateway comboGateway;

    public CriaComboInteractor(final ComboGateway comboGateway) {
        this.comboGateway = comboGateway;
    }

    public ComboResponse execute(ComboRequest comboRequest) {
        return comboGateway.criaCombo(comboRequest);
    }
}