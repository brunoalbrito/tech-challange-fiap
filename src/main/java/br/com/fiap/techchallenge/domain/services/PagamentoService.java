package br.com.fiap.techchallenge.domain.services;

import br.com.fiap.techchallenge.application.controllers.request.PagamentoRequest;
import br.com.fiap.techchallenge.application.controllers.request.QrCodeRequest;
import br.com.fiap.techchallenge.application.controllers.response.PagamentoResponse;
import br.com.fiap.techchallenge.application.controllers.response.QrCodeResponse;
import br.com.fiap.techchallenge.domain.enums.TipoPagamento;
import br.com.fiap.techchallenge.infrastructure.repository.PagamentoRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class PagamentoService {

    private PagamentoRepository repository;

    public PagamentoResponse confirmaPagamento(PagamentoRequest request) {
        return PagamentoResponse.builder()
                .tipo(TipoPagamento.PIX)
                .valorTotal(new BigDecimal(10))
                .build();
    }

    public QrCodeResponse geraQrCode(QrCodeRequest request) {
        return QrCodeResponse.builder()
                .QrData("00020101021243650016COM.MERCADOLIBRE02013063638f1192a-" +
                        "5fd1-4180-a180-8bcae3556bc35204000053039865802BR5925IZ" +
                        "ABEL AAAA DE MELO6007BARUERI62070503***63040B6D")
                .InStoreOrderId("d4e8ca59-3e1d-4c03-b1f6-580e87c654ae")
                .build();
    }
}
