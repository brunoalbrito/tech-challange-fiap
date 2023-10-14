package br.com.fiap.techchallenge.application.controllers;

import br.com.fiap.techchallenge.application.controllers.request.ClienteRequest;
import br.com.fiap.techchallenge.application.controllers.response.ClientResponse;
import br.com.fiap.techchallenge.domain.Cliente;
import br.com.fiap.techchallenge.domain.services.ClientService;
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

    private ClientService clientService;

    @PostMapping
    public ResponseEntity<ClientResponse> criaCliente(@RequestBody final ClienteRequest clienteRequest) {

        log.trace("Criando cliente");

        Cliente cliente = clientService.criaCliente(clienteRequest);
        ClientResponse clientResponse = ClientResponse.builder()
                .id(cliente.getId())
                .build();


        log.trace(String.format("Cliente criado com sucesso: { Id: %s } ", cliente.getId().toString()));

        return new ResponseEntity<>(clientResponse, HttpStatus.CREATED);
    }
}
