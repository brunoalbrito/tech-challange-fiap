package br.com.fiap.techchallenge.infrastructure.postgress;

import br.com.fiap.techchallenge.infrastructure.entity.IngredienteEntity;
import br.com.fiap.techchallenge.infrastructure.repository.IngredienteRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface IngredienteRepositoryPostgress extends IngredienteRepository, JpaRepository<IngredienteEntity, UUID> {
}
