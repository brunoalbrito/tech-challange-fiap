package br.com.fiap.techchallenge.infrastructure.repository;

import br.com.fiap.techchallenge.infrastructure.entity.ClienteEntity;

public interface ClienteRepository {
    ClienteEntity save(ClienteEntity clienteEntity);
}
