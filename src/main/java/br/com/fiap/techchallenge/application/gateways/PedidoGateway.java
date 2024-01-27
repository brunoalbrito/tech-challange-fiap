package br.com.fiap.techchallenge.application.gateways;

import br.com.fiap.techchallenge.domain.Pedido;

import java.util.UUID;

public interface PedidoGateway {

    Pedido buscaPorUUID(UUID uuid);
}
