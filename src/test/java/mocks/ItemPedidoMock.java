package mocks;

import br.com.fiap.techchallenge.domain.Item;
import br.com.fiap.techchallenge.domain.ItemPedido;

import java.math.BigDecimal;

public class ItemPedidoMock {
    public static ItemPedido createItemPedido() {
        Item item = ItemMock.createLanche();
        return ItemPedido.criaItemPedido(item, 2);
    }
}
