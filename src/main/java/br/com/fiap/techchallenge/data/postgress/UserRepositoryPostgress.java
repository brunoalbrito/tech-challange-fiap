package br.com.fiap.techchallenge.data.postgress;

import br.com.fiap.techchallenge.data.entity.User;
import br.com.fiap.techchallenge.data.repository.UserRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepositoryPostgress extends UserRepository, JpaRepository<User, Long> {
}