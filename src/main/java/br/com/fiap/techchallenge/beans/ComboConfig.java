package br.com.fiap.techchallenge.beans;

import br.com.fiap.techchallenge.application.gateways.ComboGateway;
import br.com.fiap.techchallenge.application.usecases.combo.CriaComboInteractor;
import br.com.fiap.techchallenge.application.usecases.combo.ListaComboInteractor;
import br.com.fiap.techchallenge.infrastructure.controllers.mappers.ComboDtoMapper;
import br.com.fiap.techchallenge.infrastructure.persistence.gateways.ComboRepositoryGateway;
import br.com.fiap.techchallenge.infrastructure.persistence.repository.ComboRepository;
import br.com.fiap.techchallenge.infrastructure.persistence.repository.ProdutoRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ComboConfig {

    @Bean
    CriaComboInteractor criaComboInteractor(ComboGateway comboGateway) {
        return new CriaComboInteractor(comboGateway);
    }

    @Bean
    ListaComboInteractor listaComboInteractor(ComboGateway comboGateway) {
        return new ListaComboInteractor(comboGateway);
    }

    @Bean
    ComboGateway comboGateway(ProdutoRepository produtoRepository, ComboRepository comboRepository,
                              ComboDtoMapper comboDtoMapper) {
        return new ComboRepositoryGateway(produtoRepository, comboRepository, comboDtoMapper);
    }

    @Bean
    ComboDtoMapper comboDtoMapper() {
        return new ComboDtoMapper();
    }
}