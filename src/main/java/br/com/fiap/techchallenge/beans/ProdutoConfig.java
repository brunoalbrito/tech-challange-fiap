package br.com.fiap.techchallenge.beans;

import br.com.fiap.techchallenge.application.gateways.ProdutoGateway;
import br.com.fiap.techchallenge.application.usecases.produto.CriaProdutoInteractor;
import br.com.fiap.techchallenge.infrastructure.controllers.mappers.ProdutoRequestMapper;
import br.com.fiap.techchallenge.infrastructure.persistence.gateways.ProdutoRepositoryGateway;
import br.com.fiap.techchallenge.infrastructure.persistence.repository.ProdutoRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProdutoConfig {
    @Bean
    CriaProdutoInteractor createProdutoCase(ProdutoGateway produtoGateway) {
        return new CriaProdutoInteractor(produtoGateway);
    }

    @Bean
    ProdutoGateway produtoGateway(ProdutoRepository produtoRepository) {
        return new ProdutoRepositoryGateway(produtoRepository);
    }

    @Bean
    ProdutoRequestMapper produtoRequestMapper() {
        return new ProdutoRequestMapper();
    }
}
