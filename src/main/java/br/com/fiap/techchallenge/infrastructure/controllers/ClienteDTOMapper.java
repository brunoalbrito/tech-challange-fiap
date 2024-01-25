package br.com.fiap.techchallenge.infrastructure.controllers;

import br.com.fiap.techchallenge.domain.Cliente;
import br.com.fiap.techchallenge.infrastructure.controllers.request.ClienteRequest;
import br.com.fiap.techchallenge.infrastructure.controllers.response.ClienteResponse;

public class ClienteDTOMapper {
    ClienteResponse toResponse(Cliente cliente) {
        return ClienteResponse.builder().cpf(cliente.getCpf()).build();
    }

    public Cliente toCliente(ClienteRequest request) {
        return Cliente.criaCliente(request.getCpf());
    }

}
