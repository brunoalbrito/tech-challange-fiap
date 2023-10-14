package br.com.fiap.techchallenge.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ClienteTest {

    @Test
    public void deveCriarClienteComCPFValido() {
        String cpf = "26311855879";
        Cliente cliente = Cliente.criaCliente("26311855879");

        assertNotNull(cliente);
        assertNotNull(cliente.getId());
        assertThat(cliente.getCpf()).isEqualTo(cpf);
    }

    @Test
    public void naoDeveClienteClienteQuandoCPFInvalido() {
        assertThrows(IllegalArgumentException.class, () ->
                Cliente.criaCliente("111111111111")
        );
    }

    @Test
    public void naoDeveClienteClienteQuandoCPFMenorQueOnzeDigitos() {
        assertThrows(IllegalArgumentException.class, () ->
                Cliente.criaCliente("2631185587")
        );
    }
}
