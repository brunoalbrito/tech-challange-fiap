package br.com.fiap.techchallenge.infrastructure.postgress;

import br.com.fiap.techchallenge.infrastructure.entity.User;
import br.com.fiap.techchallenge.infrastructure.repository.UserRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepositoryPostgress extends UserRepository, JpaRepository<User, Long> {
}