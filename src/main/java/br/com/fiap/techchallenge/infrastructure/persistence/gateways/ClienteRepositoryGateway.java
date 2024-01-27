package br.com.fiap.techchallenge.infrastructure.persistence.gateways;

import br.com.fiap.techchallenge.application.gateways.ClienteGateway;
import br.com.fiap.techchallenge.domain.Cliente;
import br.com.fiap.techchallenge.infrastructure.persistence.mappers.ClienteEntityMapper;
import br.com.fiap.techchallenge.infrastructure.persistence.entity.ClienteEntity;
import br.com.fiap.techchallenge.infrastructure.persistence.repository.ClienteRepository;

public class ClienteRepositoryGateway implements ClienteGateway {
    private final ClienteRepository clienteRepository;
    private final ClienteEntityMapper clienteEntityMapper;

    public ClienteRepositoryGateway(ClienteRepository clienteRepository, ClienteEntityMapper clienteEntityMapper) {
        this.clienteRepository = clienteRepository;
        this.clienteEntityMapper = clienteEntityMapper;
    }

    @Override
    public Cliente criaCliente(Cliente clienteDomainObj) {
        ClienteEntity clienteEntity = clienteEntityMapper.toEntity(clienteDomainObj);
        ClienteEntity savedObj = clienteRepository.save(clienteEntity);
        return clienteEntityMapper.toDomainObj(savedObj);

    }
}
