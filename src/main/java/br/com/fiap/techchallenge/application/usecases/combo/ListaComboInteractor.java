package br.com.fiap.techchallenge.application.usecases.combo;

import br.com.fiap.techchallenge.application.gateways.ComboGateway;
import br.com.fiap.techchallenge.domain.Combo;

import java.util.List;

public class ListaComboInteractor {

    private final ComboGateway comboGateway;

    public ListaComboInteractor(ComboGateway comboGateway) {
        this.comboGateway = comboGateway;
    }

    public List<Combo> execute() {
        return comboGateway.listaCombos();
    }
}