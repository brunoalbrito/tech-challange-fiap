package br.com.fiap.techchallenge.infrastructure.network.gateway;

import br.com.fiap.techchallenge.application.gateways.PagamentoGateway;
import br.com.fiap.techchallenge.infrastructure.network.client.MercadoLivreClient;

import java.util.UUID;

public class PagamentoClientGateway implements PagamentoGateway {

    private final MercadoLivreClient mercadoLivreClient;

    public PagamentoClientGateway(MercadoLivreClient mercadoLivreClient) {
        this.mercadoLivreClient = mercadoLivreClient;
    }

    public boolean estaPago(UUID pedidoId) {
        return mercadoLivreClient.verificaPagamento(pedidoId);
    }
}
