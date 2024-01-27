package br.com.fiap.techchallenge.application.usecases.pedido;

import br.com.fiap.techchallenge.application.gateways.PedidoGateway;
import br.com.fiap.techchallenge.domain.Pedido;

import java.util.UUID;

public class PreparoFinalizadoInteractor {

    private final PedidoGateway pedidoGateway;

    public PreparoFinalizadoInteractor(PedidoGateway pedidoGateway) {
        this.pedidoGateway = pedidoGateway;
    }

    public Pedido execute(UUID pedidoId) {
        Pedido pedido = pedidoGateway.buscaPorUUID(pedidoId);
        pedido.preparoFinalizado();

        return pedidoGateway.salva(pedido);
    }
}
