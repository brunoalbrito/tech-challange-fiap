package br.com.fiap.techchallenge.infrastructure.repository;

import br.com.fiap.techchallenge.infrastructure.entity.User;

import java.util.List;

public interface UserRepository {
    User save(User user);

    List<User> findAll();
}
