package br.com.fiap.techchallenge.application.usecases.combo;

import br.com.fiap.techchallenge.application.gateways.ComboGateway;
import br.com.fiap.techchallenge.domain.Combo;
import br.com.fiap.techchallenge.infrastructure.controllers.request.ComboRequest;

public class CriaComboInteractor {

    private final ComboGateway comboGateway;

    public CriaComboInteractor(final ComboGateway comboGateway) {
        this.comboGateway = comboGateway;
    }

    public Combo execute(ComboRequest comboRequest) {
        // TODO - verificar as validações que existem no ComboService e replicar aqui
        Combo combo = null;

        return comboGateway.salva(combo);
    }
}