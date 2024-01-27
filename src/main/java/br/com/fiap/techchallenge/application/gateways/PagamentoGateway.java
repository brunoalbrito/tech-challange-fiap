package br.com.fiap.techchallenge.application.gateways;

import br.com.fiap.techchallenge.domain.Pagamento;

import java.util.UUID;

public interface PagamentoGateway {
    Pagamento cria(UUID pedidoId);

    boolean estaPago(UUID pedidoId);
}
