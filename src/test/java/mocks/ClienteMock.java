package mocks;

import br.com.fiap.techchallenge.domain.Cliente;

import java.util.UUID;

public class ClienteMock {
    public static Cliente criaCliente() {
        return Cliente.criaCliente(UUID.randomUUID(), "26311855879");
    }
}
