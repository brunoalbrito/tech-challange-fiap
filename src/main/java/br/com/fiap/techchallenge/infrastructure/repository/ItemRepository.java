package br.com.fiap.techchallenge.infrastructure.repository;

import br.com.fiap.techchallenge.infrastructure.entity.ItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ItemRepository extends JpaRepository<ItemEntity, UUID> {

}
