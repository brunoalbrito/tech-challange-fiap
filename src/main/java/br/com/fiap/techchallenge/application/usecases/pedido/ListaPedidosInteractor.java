package br.com.fiap.techchallenge.application.usecases.pedido;

import br.com.fiap.techchallenge.application.gateways.PedidoGateway;
import br.com.fiap.techchallenge.domain.Pedido;

import java.util.List;

public class ListaPedidosInteractor {

    private final PedidoGateway pedidoGateway;

    public ListaPedidosInteractor(final PedidoGateway pedidoGateway) {
        this.pedidoGateway = pedidoGateway;
    }

    public List<Pedido> execute() {
        return pedidoGateway.listaPedido();
    }
}
