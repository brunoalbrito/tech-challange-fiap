package br.com.fiap.techchallenge.application.controllers;

import br.com.fiap.techchallenge.application.controllers.request.PedidoRequest;
import br.com.fiap.techchallenge.application.controllers.response.PedidoResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    @PostMapping
    public ResponseEntity<PedidoResponse> criaPedido(@RequestBody final PedidoRequest pedidoRequest) {


        return new ResponseEntity<>(PedidoResponse.builder().build(), org.springframework.http.HttpStatus.CREATED);
    }
}
