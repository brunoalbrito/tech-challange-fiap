package br.com.fiap.techchallenge.data.repository;

import br.com.fiap.techchallenge.data.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
