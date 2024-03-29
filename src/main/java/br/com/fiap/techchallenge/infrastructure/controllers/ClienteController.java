package br.com.fiap.techchallenge.infrastructure.controllers;

import br.com.fiap.techchallenge.application.usecases.cliente.CriaClienteInteractor;
import br.com.fiap.techchallenge.domain.Cliente;
import br.com.fiap.techchallenge.infrastructure.controllers.request.ClienteRequest;
import br.com.fiap.techchallenge.infrastructure.controllers.response.ClienteResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/api/clientes")

public class ClienteController {

    private final CriaClienteInteractor criaClienteInteractor;

    @PostMapping
    public ResponseEntity<ClienteResponse> criaCliente(@RequestBody final ClienteRequest clienteRequest) {

        log.trace("Criando cliente");

        Cliente cliente = criaClienteInteractor.execute(clienteRequest.toDomain());

        ClienteResponse clienteResponse = ClienteResponse.ofDomain(cliente);

        log.trace(String.format("Cliente criado com sucesso: { Cpf: %s } ",
                clienteResponse.getCpf()));

        return new ResponseEntity<>(clienteResponse, HttpStatus.CREATED);
    }
}
