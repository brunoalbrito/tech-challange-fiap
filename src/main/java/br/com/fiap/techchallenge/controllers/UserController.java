package br.com.fiap.techchallenge.controllers;


import br.com.fiap.techchallenge.controllers.request.UserRequest;
import br.com.fiap.techchallenge.controllers.response.UserResponse;
import br.com.fiap.techchallenge.data.entity.User;
import br.com.fiap.techchallenge.data.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@AllArgsConstructor
public class UserController {

    private UserRepository userRepository;

    @PostMapping
    public UserResponse createUser(@RequestBody UserRequest userRequest) {

        User user = User.builder().name(userRequest.getName()).build();

        userRepository.save(user);

        return UserResponse.builder().name(user.getName()).build();
    }

    @GetMapping
    public List<UserResponse> findAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(user -> UserResponse
                        .builder()
                        .name(user.getName())
                        .build())
                .toList();
    }
}
