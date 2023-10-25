package br.com.fiap.techchallenge.domain.services;

import br.com.fiap.techchallenge.application.controllers.request.PagamentoRequest;
import br.com.fiap.techchallenge.application.controllers.request.QrCodeRequest;
import br.com.fiap.techchallenge.application.controllers.response.PagamentoResponse;
import br.com.fiap.techchallenge.application.controllers.response.QrCodeResponse;
import br.com.fiap.techchallenge.domain.Pagamento;
import br.com.fiap.techchallenge.infrastructure.repository.PagamentoRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PagamentoService {

    private PagamentoRepository repository;

    public QrCodeResponse recuperaQrCode(QrCodeRequest request) {
        return Pagamento.criaQrCode(request);
    }

    public PagamentoResponse confirma(PagamentoRequest request) {
        return Pagamento.confirmaPagamento(request);
    }

    public Pagamento cria() {
        return Pagamento.criaPagamento(UUID.randomUUID(), UUID.randomUUID().toString());
    }
}