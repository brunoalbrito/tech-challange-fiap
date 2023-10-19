package br.com.fiap.techchallenge.application.controllers;

import br.com.fiap.techchallenge.application.controllers.request.ClienteRequest;
import br.com.fiap.techchallenge.application.controllers.response.ClienteResponse;
import br.com.fiap.techchallenge.domain.Cliente;
import br.com.fiap.techchallenge.domain.services.ClientService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    private ClientService clienteService;

    @PostMapping
    public ResponseEntity<ClienteResponse> criaCliente(@RequestBody final ClienteRequest clienteRequest) {

        log.trace("Criando cliente");

        Cliente cliente = clienteService.criaCliente(clienteRequest);
        ClienteResponse clienteResponse = ClienteResponse.builder()
                .id(cliente.getId())
                .build();


        log.trace(String.format("Cliente criado com sucesso: { Id: %s } ", cliente.getId().toString()));

        return new ResponseEntity<>(clienteResponse, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<ClienteResponse> buscaClientePorId(@RequestParam final String id) {
        Cliente cliente = clienteService.buscaCliente(UUID.fromString(id));

        var clienteResponse = ClienteResponse.builder();
        clienteResponse.id(cliente.getId());
        clienteResponse.cpf(cliente.getCpf());

        return new ResponseEntity<>(clienteResponse.build(), HttpStatus.OK);
    }
}
