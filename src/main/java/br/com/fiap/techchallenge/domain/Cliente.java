package br.com.fiap.techchallenge.domain;


import br.com.fiap.techchallenge.utils.CpfValidator;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Cliente {
    private String cpf;

    public static Cliente criaCliente(String cpf) {
        if (!CpfValidator.isValid(cpf)) {
            throw new IllegalArgumentException("CPF inválido");
        }

        return new Cliente(cpf);
    }

}
