package br.com.fiap.techchallenge.infrastructure.postgress;

import br.com.fiap.techchallenge.infrastructure.entity.ClienteEntity;
import br.com.fiap.techchallenge.infrastructure.repository.ClienteRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ClienteRepositoryPostgress extends ClienteRepository, JpaRepository<ClienteEntity, UUID> {
}