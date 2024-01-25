package br.com.fiap.techchallenge.domain.services;

import br.com.fiap.techchallenge.infrastructure.controllers.request.ClienteRequest;
import br.com.fiap.techchallenge.domain.Cliente;
import br.com.fiap.techchallenge.infrastructure.persistence.entity.ClienteEntity;
import br.com.fiap.techchallenge.infrastructure.persistence.repository.ClienteRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ClienteServiceTest {

    @InjectMocks
    private ClienteService clienteService;

    @Mock
    private ClienteRepository clienteRepository;

    @Test
    public void deveCriarClienteComCPFValido() {
        String cpf = "26311855879";

        when(clienteRepository.save(any(ClienteEntity.class)))
                .thenAnswer(invocation -> invocation.<ClienteEntity>getArgument(0));

        Cliente cliente = clienteService.criaCliente(ClienteRequest.criaClienteRequest(cpf));

        assertNotNull(cliente);
        assertThat(cliente.getCpf()).isEqualTo(cpf);
    }

    @Test
    public void naoDeveCriarClienteQuandoCPFInvalido() {
        assertThrows(IllegalArgumentException.class, () ->
                clienteService.criaCliente(ClienteRequest.criaClienteRequest("111111111111"))
        );
    }

    @Test
    public void naoDeveCriarClienteQuandoCPFMenorQueOnzeDigitos() {
        assertThrows(IllegalArgumentException.class, () ->
                clienteService.criaCliente(ClienteRequest.criaClienteRequest("2631185587"))
        );
    }
}