package br.com.fiap.techchallenge.application.gateways;

import br.com.fiap.techchallenge.domain.Pedido;

import java.util.List;
import java.util.UUID;

public interface PedidoGateway {

    Pedido buscaPorUUID(UUID uuid);

    Pedido salva(Pedido pedido);

    List<Pedido> listaPedido();
}
