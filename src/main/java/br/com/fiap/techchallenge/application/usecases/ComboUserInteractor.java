package br.com.fiap.techchallenge.application.usecases;

import br.com.fiap.techchallenge.application.gateways.ComboGateway;
import br.com.fiap.techchallenge.domain.Combo;
import br.com.fiap.techchallenge.infrastructure.controllers.request.ComboRequest;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ComboUserInteractor {

    private final ComboGateway comboGateway;

    public ComboUserInteractor(final ComboGateway comboGateway) {
        this.comboGateway = comboGateway;
    }

    public Combo criaCombo(ComboRequest comboRequest) {
        return comboGateway.criaCombo(comboRequest);
    }

    public List<Combo> listaCombos() {
        return comboGateway.listaCombos();
    }
}
