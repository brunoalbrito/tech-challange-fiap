package br.com.fiap.techchallenge.beans;

import br.com.fiap.techchallenge.application.gateways.IngredienteGateway;
import br.com.fiap.techchallenge.application.usecases.ingrediente.BuscaIngredientesPorIdInteractor;
import br.com.fiap.techchallenge.infrastructure.persistence.gateways.IngredienteRepositoryGateway;
import br.com.fiap.techchallenge.infrastructure.persistence.repository.IngredienteRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class IngredientConfig {
    @Bean
    BuscaIngredientesPorIdInteractor buscaIngredientesPorIdInteractor(final IngredienteGateway ingredienteGateway) {
        return new BuscaIngredientesPorIdInteractor(ingredienteGateway);
    }

    @Bean
    IngredienteGateway ingredienteGateway(IngredienteRepository ingredienteRepository) {
        return new IngredienteRepositoryGateway(ingredienteRepository);
    }
}
