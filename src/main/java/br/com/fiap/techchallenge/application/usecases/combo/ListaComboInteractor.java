package br.com.fiap.techchallenge.application.usecases.combo;

import br.com.fiap.techchallenge.application.gateways.ComboGateway;
import br.com.fiap.techchallenge.infrastructure.controllers.response.ComboResponse;

import java.util.List;

public class ListaComboInteractor {

    private final ComboGateway comboGateway;

    public ListaComboInteractor(ComboGateway comboGateway) {
        this.comboGateway = comboGateway;
    }

    public List<ComboResponse> execute() {
        return comboGateway.listaCombos();
    }
}