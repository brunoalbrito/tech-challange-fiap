package br.com.fiap.techchallenge.application.usecases.cliente;

import br.com.fiap.techchallenge.application.gateways.ClienteGateway;
import br.com.fiap.techchallenge.domain.Cliente;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class CriaClienteInteractorTest {

    private CriaClienteInteractor criaClienteInteractor;
    private ClienteGateway clienteGateway;

    @BeforeEach
    public void setUp() {
        clienteGateway = mock(ClienteGateway.class);
        criaClienteInteractor = new CriaClienteInteractor(clienteGateway);
    }

    @Test
    public void deveCriarClienteDadoCpfValido() {
        String cpf = "48336661115";
        Cliente cliente = Cliente.criaCliente(cpf);

        when(clienteGateway.criaCliente(any())).thenReturn(Cliente.criaCliente(cpf));

        Cliente result = criaClienteInteractor.execute(cliente);

        assertNotNull(result);
        assertEquals(cpf, result.getCpf());

        verify(clienteGateway, times(1)).criaCliente(any());
    }
}