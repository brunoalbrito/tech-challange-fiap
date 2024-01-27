package br.com.fiap.techchallenge.application.usecases.pedido;

import br.com.fiap.techchallenge.application.gateways.PedidoGateway;
import br.com.fiap.techchallenge.domain.Pedido;
import br.com.fiap.techchallenge.infrastructure.persistence.entity.PedidoEntity;

import java.util.UUID;

public class ConfirmaEntregaPedidoInteractor {

    private final PedidoGateway pedidoGateway;

    public ConfirmaEntregaPedidoInteractor(PedidoGateway pedidoGateway) {
        this.pedidoGateway = pedidoGateway;
    }

    public Pedido execute(UUID pedidoId) {
        Pedido pedido = pedidoGateway.buscaPorUUID(pedidoId);
        pedido.entregue();

        return pedidoGateway.salva(pedido);
    }
}
