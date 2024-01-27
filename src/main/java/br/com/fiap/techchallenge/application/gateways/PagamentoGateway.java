package br.com.fiap.techchallenge.application.gateways;

import java.util.UUID;

public interface PagamentoGateway {
    boolean estaPago(UUID pedidoId);
}
