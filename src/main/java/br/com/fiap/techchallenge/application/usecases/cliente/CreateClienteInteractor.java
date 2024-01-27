package br.com.fiap.techchallenge.application.usecases.cliente;

import br.com.fiap.techchallenge.application.gateways.ClienteGateway;
import br.com.fiap.techchallenge.domain.Cliente;

public class CreateClienteInteractor {
    private ClienteGateway clienteGateway;

    public CreateClienteInteractor(ClienteGateway clienteGateway) {
        this.clienteGateway = clienteGateway;
    }

    public Cliente createCliente(Cliente cliente) {
        return clienteGateway.criaCliente(cliente);
    }

}
