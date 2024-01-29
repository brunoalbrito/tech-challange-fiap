package br.com.fiap.techchallenge.infrastructure.persistence.repository;

import br.com.fiap.techchallenge.infrastructure.persistence.entity.PedidoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PedidoRepository extends JpaRepository<PedidoEntity, UUID> {
}
