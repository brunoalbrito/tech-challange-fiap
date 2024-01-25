package br.com.fiap.techchallenge.infrastructure.persistence.repository;

import br.com.fiap.techchallenge.infrastructure.persistence.entity.ComboEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ComboRepository extends JpaRepository<ComboEntity, UUID> {
}
