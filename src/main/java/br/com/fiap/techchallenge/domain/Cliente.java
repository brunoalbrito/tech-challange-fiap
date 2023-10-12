package br.com.fiap.techchallenge.domain;

import java.util.List;

public class Cliente {

    private Long idenficicao;
    private Long cpf;

    private List<Pedido> pedidos;

    public Pedido criaPedido(List<ItemPedido> itemPedidos) throws Exception {
        Pedido pedido = Pedido.criaPedido(itemPedidos);
        this.pedidos.add(pedido);
        return pedido;
    }
}
