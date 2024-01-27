package br.com.fiap.techchallenge.beans;

import br.com.fiap.techchallenge.application.gateways.ClienteGateway;
import br.com.fiap.techchallenge.application.usecases.cliente.CreateClienteInteractor;
import br.com.fiap.techchallenge.infrastructure.controllers.ClienteDTOMapper;
import br.com.fiap.techchallenge.infrastructure.persistence.gateways.ClienteRepositoryGateway;
import br.com.fiap.techchallenge.infrastructure.persistence.repository.ClienteRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ClienteConfig {
    @Bean
    CreateClienteInteractor createClienteInteractor(ClienteGateway clienteGateway) {
        return new CreateClienteInteractor(clienteGateway);
    }

    @Bean
    ClienteGateway clienteGateway(ClienteRepository clienteRepository) {
        return new ClienteRepositoryGateway(clienteRepository);
    }

    @Bean
    ClienteDTOMapper clienteDTOMapper() {
        return new ClienteDTOMapper();
    }

}
