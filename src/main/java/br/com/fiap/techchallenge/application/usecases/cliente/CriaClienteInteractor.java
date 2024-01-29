package br.com.fiap.techchallenge.application.usecases.cliente;

import br.com.fiap.techchallenge.application.gateways.ClienteGateway;
import br.com.fiap.techchallenge.domain.Cliente;

public class CriaClienteInteractor {
    private final ClienteGateway clienteGateway;

    public CriaClienteInteractor(ClienteGateway clienteGateway) {
        this.clienteGateway = clienteGateway;
    }

    public Cliente execute(Cliente cliente) {
        return clienteGateway.criaCliente(cliente);
    }

}
