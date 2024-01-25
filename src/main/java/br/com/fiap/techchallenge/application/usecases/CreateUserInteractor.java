package br.com.fiap.techchallenge.application.usecases;

import br.com.fiap.techchallenge.application.gateways.UserGateway;
import br.com.fiap.techchallenge.domain.Cliente;

public class CreateUserInteractor {

    private final UserGateway userGateway;

    public CreateUserInteractor(final UserGateway userGateway) {
        this.userGateway = userGateway;
    }

    public Cliente execute(final Cliente cliente) {
        // return userGateway.createUser(cliente);
        throw new UnsupportedOperationException();
    }
}
