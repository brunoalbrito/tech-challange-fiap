package br.com.fiap.techchallenge.application.usecases.pedido;

import br.com.fiap.techchallenge.application.gateways.PagamentoGateway;
import br.com.fiap.techchallenge.application.gateways.PedidoGateway;
import br.com.fiap.techchallenge.domain.Pedido;

import java.util.UUID;

public class RecebePagamentoInteractor {
    private final PedidoGateway pedidoGateway;

    private final PagamentoGateway pagamentoGateway;

    public RecebePagamentoInteractor(PedidoGateway pedidoGateway, PagamentoGateway pagamentoGateway) {
        this.pedidoGateway = pedidoGateway;
        this.pagamentoGateway = pagamentoGateway;
    }

    public void execute(UUID pedidoId) {
        Pedido pedido = pedidoGateway.buscaPorUUID(pedidoId);
        verificaPedidoPago(pedidoId);

        pedido.pagamentoRecebido();
        pedidoGateway.salva(pedido);
    }

    private void verificaPedidoPago(UUID pedidoId) {
        if (!pagamentoGateway.estaPago(pedidoId)) {
            throw new RuntimeException("De acordo com o provedor de pagamento, esse pedido não está pago");
        }
    }
}
