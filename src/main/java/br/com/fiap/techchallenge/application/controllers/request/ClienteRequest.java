package br.com.fiap.techchallenge.application.controllers.request;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ClienteRequest {
    private String cpf;
    public static ClienteRequest criaClienteRequest(String cpf) {
        return new ClienteRequest(cpf);
    }
}
