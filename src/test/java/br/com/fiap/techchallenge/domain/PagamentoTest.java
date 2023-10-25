package br.com.fiap.techchallenge.domain;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PagamentoTest {

    @Test
    public void deveCriarPagamentoValido() {
        Pagamento pagamento = Pagamento.criaPagamento(UUID.randomUUID(), "123456789");

        assertNotNull(pagamento);
        assertNotNull(pagamento.getId());
        assertNotNull(pagamento.getQrCode());
    }


    @Test
    public void naoDeveCriarPagamentoQuandoQrCodeVazio() {
        assertThrows(IllegalArgumentException.class, () ->
                Pagamento.criaPagamento(UUID.randomUUID(), "")
        );
    }

    @Test
    public void naoDeveCriarPagamentoQuandoQrCodeNulo() {
        assertThrows(IllegalArgumentException.class, () ->
                Pagamento.criaPagamento(UUID.randomUUID(), null)
        );
    }
}
