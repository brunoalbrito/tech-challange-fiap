package br.com.fiap.techchallenge.infrastructure.gateways;

import br.com.fiap.techchallenge.domain.Cliente;
import br.com.fiap.techchallenge.infrastructure.persistence.entity.ClienteEntity;

public class ClienteEntityMapper {
    ClienteEntity toEntity(Cliente clienteDomainObj) {
        return ClienteEntity.criaEntity(clienteDomainObj);
    }

    Cliente toDomainObj(ClienteEntity clienteEntity) {
        return Cliente.criaCliente(clienteEntity.getCpf());
    }

}
