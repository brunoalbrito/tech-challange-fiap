package mocks;

import br.com.fiap.techchallenge.domain.Cliente;

public class ClienteMock {
    public static Cliente criaCliente(){
        return Cliente.criaCliente("26311855879");
    }
}
