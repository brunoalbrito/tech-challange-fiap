package br.com.fiap.techchallenge.data.repository;

import br.com.fiap.techchallenge.data.entity.User;

import java.util.List;

public interface UserRepository {
    User save(User user);

    List<User> findAll();
}
