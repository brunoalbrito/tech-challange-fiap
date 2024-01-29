package br.com.fiap.techchallenge.infrastructure.controllers.response;

import br.com.fiap.techchallenge.domain.Cliente;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ClienteResponse {
    private String cpf;

    public static ClienteResponse ofDomain(Cliente cliente) {
        return ClienteResponse.builder()
                .cpf(cliente.getCpf())
                .build();
    }
}