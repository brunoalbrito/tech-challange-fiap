package br.com.fiap.techchallenge.infrastructure.network.gateway;

import br.com.fiap.techchallenge.application.gateways.PagamentoGateway;
import br.com.fiap.techchallenge.domain.Pagamento;
import br.com.fiap.techchallenge.domain.Pedido;
import br.com.fiap.techchallenge.domain.Produto;
import br.com.fiap.techchallenge.infrastructure.network.client.MercadoLivreClient;
import br.com.fiap.techchallenge.infrastructure.network.data.ItemClient;
import br.com.fiap.techchallenge.infrastructure.network.data.PagamentoClient;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.UUID;

@Slf4j
public class PagamentoClientGateway implements PagamentoGateway {

    public static final String NOTIFICATION_URL = "https://www.yourserver.com/notifications";
    private final MercadoLivreClient mercadoLivreClient;

    public PagamentoClientGateway(MercadoLivreClient mercadoLivreClient) {
        this.mercadoLivreClient = mercadoLivreClient;
    }

    @Override
    public Pagamento cria(Pedido pedido) {
        PagamentoClient pagamentoClient = PagamentoClient.fromPedido(pedido, NOTIFICATION_URL);
        String qrcode = mercadoLivreClient.criaPagamento(pagamentoClient);

        log.info("Qrcode de pagamento para pedido {} criado", pedido.getId());

        return Pagamento.criaPagamento(UUID.randomUUID(), qrcode);
    }

    public boolean estaPago(UUID pedidoId) {
        log.info("Sempre devolve a verificacao do pagamento como feito.");
        return mercadoLivreClient.verificaPagamento(pedidoId);
    }
}
