package br.com.fiap.techchallenge.application.gateways;

import br.com.fiap.techchallenge.domain.Cliente;

public interface ClienteGateway {
    Cliente criaCliente(Cliente cliente);

    Cliente buscaPorCpf(String clienteId);
}