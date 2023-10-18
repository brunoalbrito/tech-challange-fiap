package mocks;

import br.com.fiap.techchallenge.domain.Item;
import br.com.fiap.techchallenge.domain.ItemPedido;

public class ItemPedidoMock {
    public static ItemPedido createItemPedido() {
        Item item = ItemMock.criaLanche();
        return ItemPedido.criaItemPedido(item, 2);
    }
}
