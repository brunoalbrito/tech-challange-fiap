package br.com.fiap.techchallenge.infrastructure.repository;

import br.com.fiap.techchallenge.infrastructure.entity.ProdutoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProdutoRepository extends JpaRepository<ProdutoEntity, UUID> {
}
