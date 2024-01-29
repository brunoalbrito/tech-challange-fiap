package br.com.fiap.techchallenge.beans;

import br.com.fiap.techchallenge.application.gateways.PagamentoGateway;
import br.com.fiap.techchallenge.infrastructure.network.client.MercadoLivreClient;
import br.com.fiap.techchallenge.infrastructure.network.gateway.PagamentoClientGateway;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class PagamentoConfig {

    @Bean
    PagamentoGateway pagamentoGateway(MercadoLivreClient mercadoLivreClient) {
        return new PagamentoClientGateway(mercadoLivreClient);
    }

    @Bean
    MercadoLivreClient mercadoLivreClient(ObjectMapper objectMapper, RestTemplate restTemplate) {
        return new MercadoLivreClient(objectMapper, restTemplate);
    }

    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
