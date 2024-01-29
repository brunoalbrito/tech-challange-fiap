package br.com.fiap.techchallenge.infrastructure.controllers;

import br.com.fiap.techchallenge.application.usecases.pedido.BuscaPedidoInteractor;
import br.com.fiap.techchallenge.application.usecases.pedido.ConfirmaEntregaPedidoInteractor;
import br.com.fiap.techchallenge.application.usecases.pedido.CriaPedidoInteractor;
import br.com.fiap.techchallenge.application.usecases.pedido.ListaPedidosInteractor;
import br.com.fiap.techchallenge.application.usecases.pedido.PreparoFinalizadoInteractor;
import br.com.fiap.techchallenge.application.usecases.pedido.RecebePagamentoInteractor;
import br.com.fiap.techchallenge.domain.Pedido;
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

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/pedidos")
@AllArgsConstructor
public class PedidoController {

    private final CriaPedidoInteractor criaPedidoInteractor;

    private final BuscaPedidoInteractor buscaPedidoInteractor;

    private final RecebePagamentoInteractor recebePagamentoInteractor;

    private final PreparoFinalizadoInteractor preparoFinalizadoInteractor;

    private final ConfirmaEntregaPedidoInteractor confirmaEntregaPedidoInteractor;

    private final ListaPedidosInteractor listaPedidosInteractor;

    @PostMapping
    public ResponseEntity<PedidoResponse> criaPedido(@RequestBody final PedidoRequest pedidoRequest) {
        Pedido pedido = criaPedidoInteractor.execute(pedidoRequest);

        PedidoResponse pedidoResponse = PedidoResponse.ofDomain(pedido);
        return new ResponseEntity<>(pedidoResponse, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<PedidoResponse>> listarPedidos() {
        List<Pedido> pedidos = listaPedidosInteractor.execute();
        List<PedidoResponse> pedidoResponses = pedidos
                .stream()
                .map(PedidoResponse::ofDomain)
                .toList();

        return ResponseEntity.ok(pedidoResponses);
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

    @PatchMapping("{id}/preparo-finalizado")
    public ResponseEntity<PedidoResponse> preparoFinalizado(@PathVariable("id") final UUID pedidoId) {
        Pedido pedido = preparoFinalizadoInteractor.execute(pedidoId);
        PedidoResponse pedidoResponse = PedidoResponse.ofDomain(pedido);

        return ResponseEntity.ok(pedidoResponse);
    }

    @PatchMapping("{id}/entregue")
    public ResponseEntity<PedidoResponse> entregue(@PathVariable("id") final UUID pedidoId) {
        Pedido pedido = confirmaEntregaPedidoInteractor.execute(pedidoId);
        PedidoResponse pedidoResponse = PedidoResponse.ofDomain(pedido);

        return ResponseEntity.ok(pedidoResponse);
    }
}
