package br.com.fiap.techchallenge.beans;

import br.com.fiap.techchallenge.application.gateways.PagamentoGateway;
import br.com.fiap.techchallenge.infrastructure.network.client.MercadoLivreClient;
import br.com.fiap.techchallenge.infrastructure.network.gateway.PagamentoClientGateway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PagamentoConfig {

    @Bean
    PagamentoGateway pagamentoGateway(MercadoLivreClient mercadoLivreClient) {
        return new PagamentoClientGateway(mercadoLivreClient);
    }

    @Bean
    MercadoLivreClient mercadoLivreClient() {
        return new MercadoLivreClient();
    }
}