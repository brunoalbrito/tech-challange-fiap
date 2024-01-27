package br.com.fiap.techchallenge.application.usecases.pedido;

import br.com.fiap.techchallenge.application.gateways.PedidoGateway;
import br.com.fiap.techchallenge.domain.Pedido;

import java.util.UUID;

public class BuscaPedidoInteractor {

    private final PedidoGateway pedidoGateway;

    public BuscaPedidoInteractor(PedidoGateway pedidoGateway) {
        this.pedidoGateway = pedidoGateway;
    }

    public Pedido execute(UUID pedidoId) {
        return pedidoGateway.buscaPorUUID(pedidoId);
    }

}
