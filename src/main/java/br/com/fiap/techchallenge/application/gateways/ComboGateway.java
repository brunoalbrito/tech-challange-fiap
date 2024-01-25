package br.com.fiap.techchallenge.application.gateways;

import br.com.fiap.techchallenge.domain.Combo;
import br.com.fiap.techchallenge.infrastructure.controllers.request.ComboRequest;

import java.util.List;

public interface ComboGateway {
    Combo criaCombo(ComboRequest comboRequest);
    List<Combo> listaCombos();
}
