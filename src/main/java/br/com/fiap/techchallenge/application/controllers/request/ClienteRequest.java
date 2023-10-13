package br.com.fiap.techchallenge.application.controllers.request;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ClienteRequest {
    private String cpf;
}
