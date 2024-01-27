package br.com.fiap.techchallenge.beans;

import br.com.fiap.techchallenge.application.gateways.ClienteGateway;
import br.com.fiap.techchallenge.application.gateways.PagamentoGateway;
import br.com.fiap.techchallenge.application.gateways.PedidoGateway;
import br.com.fiap.techchallenge.application.gateways.ProdutoGateway;
import br.com.fiap.techchallenge.application.usecases.pedido.BuscaPedidoInteractor;
import br.com.fiap.techchallenge.application.usecases.pedido.ConfirmaEntregaPedidoInteractor;
import br.com.fiap.techchallenge.application.usecases.pedido.CriaPedidoInteractor;
import br.com.fiap.techchallenge.application.usecases.pedido.PreparoFinalizadoInteractor;
import br.com.fiap.techchallenge.application.usecases.pedido.RecebePagamentoInteractor;
import br.com.fiap.techchallenge.infrastructure.persistence.gateways.PedidoRepositoryGateway;
import br.com.fiap.techchallenge.infrastructure.persistence.repository.PedidoRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PedidoConfig {

    @Bean
    PedidoGateway pedidoGateway(final PedidoRepository pedidoRepository) {
        return new PedidoRepositoryGateway(pedidoRepository);
    }

    @Bean
    CriaPedidoInteractor criaPedidoInteractor(final PedidoGateway pedidoGateway, final ProdutoGateway produtoGateway, final ClienteGateway clienteGateway, final PagamentoGateway pagamentoGateway) {
        return new CriaPedidoInteractor(pedidoGateway, produtoGateway, clienteGateway, pagamentoGateway);
    }

    @Bean
    BuscaPedidoInteractor buscaPedidoInteractor(final PedidoGateway pedidoGateway) {
        return new BuscaPedidoInteractor(pedidoGateway);
    }

    @Bean
    RecebePagamentoInteractor recebePagamentoInteractor(final PedidoGateway pedidoGateway, final PagamentoGateway pagamentoGateway) {
        return new RecebePagamentoInteractor(pedidoGateway, pagamentoGateway);
    }

    @Bean
    PreparoFinalizadoInteractor preparoFinalizadoInteractor(final PedidoGateway pedidoGateway) {
        return new PreparoFinalizadoInteractor(pedidoGateway);
    }

    @Bean
    ConfirmaEntregaPedidoInteractor confirmaEntregaPedidoInteractor(final PedidoGateway pedidoGateway) {
        return new ConfirmaEntregaPedidoInteractor(pedidoGateway);
    }
}
