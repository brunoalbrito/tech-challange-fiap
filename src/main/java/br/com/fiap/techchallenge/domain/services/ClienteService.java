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
        Cliente cliente = Cliente.criaCliente(UUID.randomUUID(), clienteRequest.getCpf());

        ClienteEntity clienteEntity = ClienteEntity.criaEntity(cliente);
        clienteRepository.save(clienteEntity);

        return cliente;
    }

    public Cliente buscaCliente(final UUID id) {
        ClienteEntity clienteEntity = clienteRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Cliente não encontrado"));
        return Cliente.criaCliente(clienteEntity.getId(), clienteEntity.getCpf());
    }
}
