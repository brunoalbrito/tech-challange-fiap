package br.com.fiap.techchallenge.infrastructure.controllers;

import java.util.UUID;

import br.com.fiap.techchallenge.infrastructure.controllers.request.PedidoRequest;
import br.com.fiap.techchallenge.infrastructure.controllers.response.PedidoResponse;
import br.com.fiap.techchallenge.domain.Pedido;
import br.com.fiap.techchallenge.domain.services.PedidoService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/pedidos")
@AllArgsConstructor
public class PedidoController {

    final PedidoService service;

    @PostMapping
    public ResponseEntity<PedidoResponse> criaPedido(@RequestBody final PedidoRequest pedidoRequest) {
        Pedido pedido = service.cria(pedidoRequest);
        return new ResponseEntity<>(PedidoResponse.ofDomain(pedido), org.springframework.http.HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<PedidoResponse> buscarPedido(@PathVariable("id") final UUID pedidoId) {
        return ResponseEntity.ok(PedidoResponse.ofDomain(service.buscar(pedidoId)));
    }

    @PatchMapping("{id}/callback")
    public ResponseEntity<Void> callbackProvedorPagamento(@PathVariable("id") final UUID pedidoId) {
        service.pedidoPago(pedidoId);
        return ResponseEntity.accepted().build();
    }

    @PatchMapping("{id}/entregue")
    public ResponseEntity<PedidoResponse> entregue(@PathVariable("id") final UUID pedidoId) {
        return ResponseEntity.ok(PedidoResponse.ofDomain(service.entregue(pedidoId)));
    }

    @PatchMapping("{id}/preparo-finalizado")
    public ResponseEntity<PedidoResponse> preparoFinalizado(@PathVariable("id") final UUID pedidoId) {
        return ResponseEntity.ok(PedidoResponse.ofDomain(service.preparoFinalizado(pedidoId)));
    }
}
