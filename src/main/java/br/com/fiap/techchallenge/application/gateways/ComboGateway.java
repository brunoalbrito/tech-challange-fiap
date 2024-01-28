package br.com.fiap.techchallenge.application.gateways;


import br.com.fiap.techchallenge.domain.Combo;

import java.util.List;

public interface ComboGateway {

    Combo salva(Combo combo);
    List<Combo> listaCombos();
}
