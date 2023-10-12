package mocks;

import br.com.fiap.techchallenge.domain.Item;
import br.com.fiap.techchallenge.domain.ItemPedido;
import br.com.fiap.techchallenge.domain.Pedido;

import java.util.Arrays;
import java.util.List;

public class PedidoMock {
    public static Pedido criaPedido(){
        Item item = ItemMock.createLanche();
        List<ItemPedido> itemPedidos = Arrays.asList(ItemPedido.criaItemPedido(item, 2), ItemPedido.criaItemPedido(item, 2));
        return  Pedido.criaPedido(itemPedidos);
    }
}
