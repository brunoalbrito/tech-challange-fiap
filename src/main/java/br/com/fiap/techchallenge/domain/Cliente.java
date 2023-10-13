package br.com.fiap.techchallenge.domain;


import br.com.fiap.techchallenge.utils.ValidadorCPF;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Cliente {

    private UUID id;
    private String cpf;
    public static Cliente criaCliente(String cpf) {
        if (!ValidadorCPF.isValido(cpf)) {
            throw new IllegalArgumentException("CPF inválido");
        }

        return new Cliente(UUID.randomUUID(), cpf);
    }
}
