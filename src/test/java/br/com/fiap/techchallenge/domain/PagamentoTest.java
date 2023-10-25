package br.com.fiap.techchallenge.domain;

import br.com.fiap.techchallenge.application.controllers.request.QrCodeRequest;
import br.com.fiap.techchallenge.application.controllers.response.QrCodeResponse;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
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

    @Test
    public void deveCriarQrCodeValido() {
        QrCodeRequest request = QrCodeRequest.builder()
                .sku("123")
                .titulo("combo familia")
                .categoria("lanchonete")
                .valorTotal(new BigDecimal(50))
                .quantidade(4)
                .valorTotal(new BigDecimal(55))
                .urlNotificacao("http://fiap.burger.com/pagamento/confirmacao")
                .build();

        QrCodeResponse qrCode = Pagamento.criaQrCode(request);
        assertNotNull(qrCode);
    }
}
