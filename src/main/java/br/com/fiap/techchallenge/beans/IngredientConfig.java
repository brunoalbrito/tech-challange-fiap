package br.com.fiap.techchallenge.beans;

import br.com.fiap.techchallenge.application.gateways.IngredienteGateway;
import br.com.fiap.techchallenge.application.usecases.ingrediente.CriaIngredienteInteractor;
import br.com.fiap.techchallenge.infrastructure.persistence.gateways.IngredienteRepositoryGateway;
import br.com.fiap.techchallenge.infrastructure.persistence.repository.IngredienteRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class IngredientConfig {
    @Bean
    CriaIngredienteInteractor criaIngredienteInteractor(IngredienteGateway ingredienteGateway) {
        return new CriaIngredienteInteractor(ingredienteGateway);
    }
    @Bean
    IngredienteGateway ingredienteGateway(IngredienteRepository ingredienteRepository) {
        return new IngredienteRepositoryGateway(ingredienteRepository);
    }
}
