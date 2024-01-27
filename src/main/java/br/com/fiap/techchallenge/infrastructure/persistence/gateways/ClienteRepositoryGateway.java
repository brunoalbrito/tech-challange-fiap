package br.com.fiap.techchallenge.infrastructure.persistence.gateways;

import br.com.fiap.techchallenge.application.gateways.ClienteGateway;
import br.com.fiap.techchallenge.domain.Cliente;
import br.com.fiap.techchallenge.infrastructure.persistence.entity.ClienteEntity;
import br.com.fiap.techchallenge.infrastructure.persistence.repository.ClienteRepository;

public class ClienteRepositoryGateway implements ClienteGateway {
    private final ClienteRepository clienteRepository;

    public ClienteRepositoryGateway(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @Override
    public Cliente criaCliente(Cliente cliente) {
        ClienteEntity clienteEntity = ClienteEntity.criaEntity(cliente);
        return clienteRepository.save(clienteEntity).toDomain();

    }
}
