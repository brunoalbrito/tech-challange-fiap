package br.com.fiap.techchallenge.application.usecases;

import br.com.fiap.techchallenge.application.gateways.ComboGateway;
import br.com.fiap.techchallenge.domain.Combo;
import br.com.fiap.techchallenge.infrastructure.controllers.request.ComboRequest;
import br.com.fiap.techchallenge.infrastructure.controllers.response.ComboResponse;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ComboInteractor {

    private final ComboGateway comboGateway;

    public ComboInteractor(final ComboGateway comboGateway) {
        this.comboGateway = comboGateway;
    }

    public ComboResponse criaCombo(ComboRequest comboRequest) {
        return comboGateway.criaCombo(comboRequest);
    }

    public List<ComboResponse> listaCombos() {
        return comboGateway.listaCombos();
    }
}
