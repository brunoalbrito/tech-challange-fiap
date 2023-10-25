package br.com.fiap.techchallenge.domain.services;

import br.com.fiap.techchallenge.application.controllers.request.ClienteRequest;
import br.com.fiap.techchallenge.domain.Cliente;
import br.com.fiap.techchallenge.infrastructure.entity.ClienteEntity;
import br.com.fiap.techchallenge.infrastructure.repository.ClienteRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class ClienteService {

    private ClienteRepository clienteRepository;

    public Cliente criaCliente(final ClienteRequest clienteRequest) {
        Cliente cliente = Cliente.criaCliente(clienteRequest.getCpf());

        ClienteEntity clienteEntity = ClienteEntity.criaEntity(cliente);
        clienteRepository.save(clienteEntity);

        return cliente;
    }
}
