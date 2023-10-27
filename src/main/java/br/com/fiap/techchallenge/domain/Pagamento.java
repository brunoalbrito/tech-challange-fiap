package br.com.fiap.techchallenge.domain;

import br.com.fiap.techchallenge.application.controllers.request.PagamentoRequest;
import br.com.fiap.techchallenge.application.controllers.request.QrCodeRequest;
import br.com.fiap.techchallenge.application.controllers.response.PagamentoResponse;
import br.com.fiap.techchallenge.application.controllers.response.QrCodeResponse;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Pagamento {
    private UUID id;

    private String qrCode;

    public static Pagamento criaPagamento(UUID randomUUID, String qrCode) {
        validateQrCode(qrCode);
        return new Pagamento(randomUUID, qrCode);
    }

    private static void validateQrCode(String qrCode) {
        if (qrCode == null || qrCode.isEmpty()) {
            throw new IllegalArgumentException("QrCode não deve ser nulo ou vazio.");
        }
    }

    public static PagamentoResponse confirmaPagamento(PagamentoRequest pagamento) {
        if(pagamento == null)
            throw new IllegalArgumentException("Algo inesperado ocorreu");

        return PagamentoResponse.builder()
                .tipo(pagamento.getTipoPagamento())
                .valorTotal(pagamento.getValorTotal())
                .build();
    }

    public static QrCodeResponse criaQrCode(QrCodeRequest pagamento) {
        String response = comunicaMercadoPago(pagamento);

        validateQrCode(response);

        return QrCodeResponse.builder()
                .QrData(response)
                .InStoreOrderId("d4e8ca59-3e1d-4c03-b1f6-580e87c654ae")
                .build();
    }

    private static String comunicaMercadoPago(QrCodeRequest pagamento) {
        return "00020101021243650016COM.MERCADOLIBRE02013063638f1192a-" +
                "5fd1-4180-a180-8bcae3556bc35204000053039865802BR5925IZ" +
                "ABEL AAAA DE MELO6007BARUERI62070503***63040B6D";
    }
}
