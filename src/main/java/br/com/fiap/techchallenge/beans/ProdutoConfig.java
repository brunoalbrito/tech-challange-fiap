package br.com.fiap.techchallenge.beans;

import br.com.fiap.techchallenge.application.gateways.IngredienteGateway;
import br.com.fiap.techchallenge.application.gateways.ProdutoGateway;
import br.com.fiap.techchallenge.application.usecases.produto.CriaProdutoInteractor;
import br.com.fiap.techchallenge.infrastructure.persistence.gateways.ProdutoRepositoryGateway;
import br.com.fiap.techchallenge.infrastructure.persistence.repository.ProdutoRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProdutoConfig {
    @Bean
    CriaProdutoInteractor createProdutoCase(ProdutoGateway produtoGateway, IngredienteGateway ingredienteGateway) {
        return new CriaProdutoInteractor(produtoGateway, ingredienteGateway);
    }

    @Bean
    ProdutoGateway produtoGateway(ProdutoRepository produtoRepository) {
        return new ProdutoRepositoryGateway(produtoRepository);
    }
}
