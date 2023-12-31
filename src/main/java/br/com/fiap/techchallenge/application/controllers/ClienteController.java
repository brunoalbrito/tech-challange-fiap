package br.com.fiap.techchallenge.application.controllers;

import br.com.fiap.techchallenge.application.controllers.request.ClienteRequest;
import br.com.fiap.techchallenge.application.controllers.response.ClienteResponse;
import br.com.fiap.techchallenge.domain.Cliente;
import br.com.fiap.techchallenge.domain.services.ClienteService;
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

    private ClienteService clienteService;

    @PostMapping
    public ResponseEntity<ClienteResponse> criaCliente(@RequestBody final ClienteRequest clienteRequest) {

        log.trace("Criando cliente");

        Cliente cliente = clienteService.criaCliente(clienteRequest);
        ClienteResponse clienteResponse = ClienteResponse.builder()
                .cpf(cliente.getCpf())
                .build();


        log.trace(String.format("Cliente criado com sucesso: { Id: %s } ", cliente.getCpf()));

        return new ResponseEntity<>(clienteResponse, HttpStatus.CREATED);
    }
}
