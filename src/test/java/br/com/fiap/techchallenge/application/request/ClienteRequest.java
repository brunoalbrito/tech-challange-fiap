package br.com.fiap.techchallenge.application.request;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(staticName = "criaClientRequest")
public class ClienteRequest {
    private String cpf;
}
