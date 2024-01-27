package br.com.fiap.techchallenge.infrastructure.controllers;

import br.com.fiap.techchallenge.application.usecases.pedido.BuscaPedidoInteractor;
import br.com.fiap.techchallenge.application.usecases.pedido.ConfirmaEntregaPedidoInteractor;
import br.com.fiap.techchallenge.application.usecases.pedido.RecebePagamentoInteractor;
import br.com.fiap.techchallenge.domain.Pedido;
import br.com.fiap.techchallenge.domain.services.PedidoService;
import br.com.fiap.techchallenge.infrastructure.controllers.request.PedidoRequest;
import br.com.fiap.techchallenge.infrastructure.controllers.response.PedidoResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/pedidos")
@AllArgsConstructor
public class PedidoController {

    private final PedidoService service;

    private final BuscaPedidoInteractor buscaPedidoInteractor;

    private final RecebePagamentoInteractor recebePagamentoInteractor;

    private final ConfirmaEntregaPedidoInteractor confirmaEntregaPedidoInteractor;

    @PostMapping
    public ResponseEntity<PedidoResponse> criaPedido(@RequestBody final PedidoRequest pedidoRequest) {
        Pedido pedido = service.cria(pedidoRequest);
        PedidoResponse pedidoResponse = PedidoResponse.ofDomain(pedido);

        return new ResponseEntity<>(pedidoResponse, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<PedidoResponse> buscarPedido(@PathVariable("id") final UUID pedidoId) {
        Pedido pedido = buscaPedidoInteractor.execute(pedidoId);
        PedidoResponse pedidoResponse = PedidoResponse.ofDomain(pedido);

        return ResponseEntity.ok(pedidoResponse);
    }

    @PatchMapping("{id}/callback")
    public ResponseEntity<Void> callbackProvedorPagamento(@PathVariable("id") final UUID pedidoId) {
        recebePagamentoInteractor.execute(pedidoId);

        return ResponseEntity.accepted().build();
    }

    @PatchMapping("{id}/entregue")
    public ResponseEntity<PedidoResponse> entregue(@PathVariable("id") final UUID pedidoId) {
        Pedido pedido = confirmaEntregaPedidoInteractor.execute(pedidoId);
        PedidoResponse pedidoResponse = PedidoResponse.ofDomain(pedido);

        return ResponseEntity.ok(pedidoResponse);
    }

    @PatchMapping("{id}/preparo-finalizado")
    public ResponseEntity<PedidoResponse> preparoFinalizado(@PathVariable("id") final UUID pedidoId) {
        return ResponseEntity.ok(PedidoResponse.ofDomain(service.preparoFinalizado(pedidoId)));
    }
}
