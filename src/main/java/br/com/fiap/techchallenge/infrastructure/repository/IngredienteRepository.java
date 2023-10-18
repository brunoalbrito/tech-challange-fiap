package br.com.fiap.techchallenge.infrastructure.repository;

import br.com.fiap.techchallenge.infrastructure.entity.IngredienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface IngredienteRepository extends JpaRepository<IngredienteEntity, UUID> {
}
