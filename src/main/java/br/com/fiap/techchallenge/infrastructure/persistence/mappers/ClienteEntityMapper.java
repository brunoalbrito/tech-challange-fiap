package br.com.fiap.techchallenge.infrastructure.persistence.mappers;

import br.com.fiap.techchallenge.domain.Cliente;
import br.com.fiap.techchallenge.infrastructure.persistence.entity.ClienteEntity;

public class ClienteEntityMapper {
    public ClienteEntity toEntity(Cliente clienteDomainObj) {
        return ClienteEntity.criaEntity(clienteDomainObj);
    }

    public Cliente toDomainObj(ClienteEntity clienteEntity) {
        return Cliente.criaCliente(clienteEntity.getCpf());
    }

}
