package br.com.fiap.techchallenge.beans;

import br.com.fiap.techchallenge.application.gateways.ComboGateway;
import br.com.fiap.techchallenge.application.usecases.ComboInteractor;
import br.com.fiap.techchallenge.infrastructure.persistence.gateways.ComboRepositoryGateway;
import br.com.fiap.techchallenge.infrastructure.persistence.mappers.ComboEntityMapper;
import br.com.fiap.techchallenge.infrastructure.persistence.repository.ComboRepository;
import br.com.fiap.techchallenge.infrastructure.persistence.repository.ProdutoRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ComboConfig {

    @Bean
    ComboInteractor createComboCase(ComboGateway comboGateway) {
        return new ComboInteractor(comboGateway);
    }

    @Bean
    ComboGateway comboGateway(ProdutoRepository produtoRepository, ComboRepository comboRepository,
                              ComboEntityMapper comboEntityMapper) {
        return new ComboRepositoryGateway(produtoRepository, comboRepository, comboEntityMapper);
    }

    @Bean
    ComboEntityMapper userEntityMapper() {
        return new ComboEntityMapper();
    }
}
