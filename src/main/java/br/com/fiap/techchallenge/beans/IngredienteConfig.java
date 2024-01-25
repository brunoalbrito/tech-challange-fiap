package br.com.fiap.techchallenge.beans;

import br.com.fiap.techchallenge.application.gateways.IngredienteGateway;
import br.com.fiap.techchallenge.application.usecases.IngredienteInteractor;
import br.com.fiap.techchallenge.infrastructure.persistence.gateways.IngredienteRepositoryGateway;
import br.com.fiap.techchallenge.infrastructure.persistence.mappers.IngredienteEntityMapper;
import br.com.fiap.techchallenge.infrastructure.persistence.repository.IngredienteRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class IngredienteConfig {

    @Bean
    IngredienteInteractor ingredienteInteractor(IngredienteGateway ingredienteGateway) {
        return new IngredienteInteractor(ingredienteGateway);
    }

    @Bean
    IngredienteGateway ingredienteGateway(IngredienteRepository ingredienteRepository) {
        return new IngredienteRepositoryGateway(ingredienteRepository);
    }

    @Bean
    IngredienteEntityMapper ingredienteEntityMapper() {
        return new IngredienteEntityMapper();
    }
}
