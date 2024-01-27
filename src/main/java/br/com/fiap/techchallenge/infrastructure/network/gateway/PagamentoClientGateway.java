package br.com.fiap.techchallenge.infrastructure.network.gateway;

import br.com.fiap.techchallenge.application.gateways.PagamentoGateway;
import br.com.fiap.techchallenge.domain.Pagamento;
import br.com.fiap.techchallenge.infrastructure.network.client.MercadoLivreClient;
import lombok.extern.slf4j.Slf4j;

import java.util.UUID;

@Slf4j
public class PagamentoClientGateway implements PagamentoGateway {

    private final MercadoLivreClient mercadoLivreClient;

    public PagamentoClientGateway(MercadoLivreClient mercadoLivreClient) {
        this.mercadoLivreClient = mercadoLivreClient;
    }

    @Override
    public Pagamento cria(UUID pedidoId) {
        log.info("Qrcode de pagamento para pedido {} criado", pedidoId);
        return Pagamento.criaPagamento(UUID.randomUUID(), UUID.randomUUID().toString());
    }

    public boolean estaPago(UUID pedidoId) {
        log.info("Sempre devolve a verificacao do pagamento como feito.");
        return mercadoLivreClient.verificaPagamento(pedidoId);
    }
}
